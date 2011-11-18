package com.orangeleap.webtools.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.soap.SOAPFaultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry;
import com.orangeleap.client.CustomField;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.CustomTableField;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.client.ReadCustomTableByNameRequest;
import com.orangeleap.client.ReadCustomTableByNameResponse;
import com.orangeleap.client.SaveOrUpdateCustomTableRowRequest;
import com.orangeleap.client.SaveOrUpdateCustomTableRowResponse;
import com.orangeleap.client.WSClient;
import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.CustomEntity;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetData;
import com.orangeleap.webtools.domain.WidgetExample;
import com.orangeleap.webtools.service.PicklistService;
import com.orangeleap.webtools.service.WidgetService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("widgetService")
public class WidgetServiceImpl implements WidgetService {
	private static final Log logger = LogFactory.getLog(WidgetServiceImpl.class);

	@Autowired
	WidgetDAO widgetDAO = null;

	@Autowired
	private PicklistService picklistService;

	@Resource(name = "customTableCache")
	Cache customTableCache;

	public Widget create() {
		return new Widget();
	}

	public Widget selectWidgetById(Long id) {
		return widgetDAO.selectWidgetByPrimaryKey(id);
	}

	public Widget selectWidgetByGuid(String guid) {
		WidgetExample example = new WidgetExample();

		example.createCriteria().andWidgetGuidEqualTo(guid);

		List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
		if (widgets == null || widgets.size() == 0) {
			return null;
		}

		return widgets.get(0);
	}

	public WidgetData process(WidgetData data) {
		return data;
	}

	public List<Widget> listWidgets(String userName, String password) {
		WidgetExample example = new WidgetExample();

		example.createCriteria().andWidgetUsernameEqualTo(userName)
				.andWidgetPasswordEqualTo(password);

		return widgetDAO.selectWidgetByExample(example);
	}

	public void updateViewCount(String guid, String refererrer) {
		WidgetExample example = new WidgetExample();
		example.createCriteria().andWidgetGuidEqualTo(guid);
		List<Widget> widgets = widgetDAO.selectWidgetByExample(example);

		//
		// guid is a unique key so this will only return one widget
		Widget widget = widgets.get(0);

		widget.setWidgetViewCount(widget.getWidgetViewCount() + 1);
		widgetDAO.updateWidgetByPrimaryKey(widget);
	}

	public Widget saveOrUpdate(Widget widget) {
		if (widget.getWidgetCreateDate() == null) {
			widget.setWidgetCreateDate(new Date());
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String userName = auth.getName();
		String password = (String) auth.getCredentials();
		String siteName = userName.substring(userName.indexOf('@') + 1);
		widget.setWidgetUsername(userName);
		widget.setWidgetPassword(password);


		Widget result = null;

		if (widget.getWidgetId() != null) {
			widgetDAO.updateWidgetByPrimaryKey(widget);
		}
		else {
			widgetDAO.insertWidget(widget);
		}

		return widget;
	}

	public CustomTableRow CreateCustomTableRow(String guid, HttpServletRequest request) throws Exception {
		List<CustomEntity> ceList = getCustomEntity(guid);
		Iterator<CustomEntity> it = ceList.iterator();

		CustomTableRow row = new CustomTableRow();
		CustomFieldMap customFieldMap = new CustomFieldMap();
		row.setCustomFieldMap(customFieldMap);

		while (it.hasNext()) {
			CustomEntity ce = it.next();
			Entry entry = new Entry();

			if (ce.getType().equals("section")) {
				continue;
			}
			CustomField val = new CustomField();
			val.setName(ce.getName());
			String value = request.getParameter(ce.getName());
			val.setValue((value == null) ? "" : value);
			val.setEntityType("customtablerow");
			val.setSequenceNumber(0);
			entry.setKey(ce.getName());
			entry.setValue(val);
			row.getCustomFieldMap().getEntry().add(entry);
		}

		//
		// call up orangeleap and save the row
		CustomTable table = getCustomTable(guid);
		WidgetExample example = new WidgetExample();
		example.createCriteria().andWidgetGuidEqualTo(guid);

		List<Widget> widgets = widgetDAO.selectWidgetByExample(example);

		if (widgets.size() > 0) {

			//
			// guid is a unique key so this will only return one widget
			Widget widget = widgets.get(0);

			String wsusername = widgets.get(0).getWidgetUsername();
			String wspassword = widgets.get(0).getWidgetPassword();

			WSClient wsClient = null;
			OrangeLeap oleap = null;

			wsClient = new WSClient();
			oleap = wsClient.getOrangeLeap(System.getProperty("webtools.wsdllocation"), wsusername, wspassword);


			row.setCustomTableId(table.getId());
			row.setCustomTableRowActive(true);

			SaveOrUpdateCustomTableRowRequest rowrequest = new SaveOrUpdateCustomTableRowRequest();
			SaveOrUpdateCustomTableRowResponse rowresponse = null;
			rowrequest.setCustomTableRow(row);
			try {
				rowresponse = oleap.saveOrUpdateCustomTableRow(rowrequest);
			}
			catch (Exception e) {
				if (e instanceof SOAPFaultException) {
					SOAPFaultException sfe = (SOAPFaultException) e;
					logger.error(sfe.getMessage());
					String message = sfe.getMessage();
					if (message.contains(":")) {
						message = message.substring(message.lastIndexOf(":") + 1);
					}
					throw new Exception(message);
				}

			}


			if (rowresponse != null) {
				return rowresponse.getCustomTableRow();
			}

		}

		return null;
	}

	public CustomTable getCustomTable(String guid) {
		WidgetExample example = new WidgetExample();
		example.createCriteria().andWidgetGuidEqualTo(guid);

		List<Widget> widgets = widgetDAO.selectWidgetByExample(example);

		if (widgets.size() > 0) {

			//
			// guid is a unique key so this will only return one widget
			Widget widget = widgets.get(0);

			Cache cache = (Cache) customTableCache;
			Element elem = cache.get(guid + widget.getCustomEntityName());
			if (elem != null) {
				return (CustomTable) elem.getObjectValue();
			}


			String wsusername = widgets.get(0).getWidgetUsername();
			String wspassword = widgets.get(0).getWidgetPassword();

			WSClient wsClient = null;
			OrangeLeap oleap = null;

			wsClient = new WSClient();
			oleap = wsClient.getOrangeLeap(System.getProperty("webtools.wsdllocation"), wsusername, wspassword);
			//
			// first get the table definition
			ReadCustomTableByNameRequest request = new ReadCustomTableByNameRequest();
			ReadCustomTableByNameResponse response = null;
			request.setName(widget.getCustomEntityName());

			response = oleap.readCustomTableByName(request);

			if (response != null) {
				cache.put(new Element(guid + response.getCustomTable().getCustomTableName(), response.getCustomTable()));
			}
			return response.getCustomTable();
		}
		return null;
	}

	public List<CustomEntity> getCustomEntity(String guid) {
		CustomTable table = getCustomTable(guid);
		if (table != null) {

			List<CustomEntity> entities = generateCustomEntities(table);

			return entities;
		}
		return null;
	}

	private List<CustomEntity> generateCustomEntities(CustomTable table) {

		List<CustomTableField> ctfields = table.getFields();
		Iterator<CustomTableField> ctit = ctfields.iterator();

		List<CustomEntity> retList = new ArrayList<CustomEntity>();
		CustomEntity ce = new CustomEntity();

		//
		// add the id
		ce.setName("id");
		ce.setType("text");
		ce.setHeader("Id");
		ce.setSearchable(false);
		ce.setHidden(true);
		ce.setPicklistId("");
		ce.setRequired(false);
		ce.setExpression("");
		ce.setValue("");
		ce.setRegEx("");
		ce.setRegExExample("");
		retList.add(ce);

		while (ctit.hasNext()) {
			CustomTableField ctfield = ctit.next();


			//
			// don't list section definitions or items with a '.' in their name...
			// only list item's that are marked as 'Include in web widgets'
			if (ctfield.getCustomTableFieldName().contains(".")
					|| ctfield.isCustomTableFieldWWViewable() == false) {
				continue;
			}

			ce = new CustomEntity();
			ce.setName(ctfield.getCustomTableFieldName());

			if (ctfield.getCustomTableFieldGuiOptions() != null && ctfield.getCustomTableFieldGuiOptions().contains("maskInput")) {
				ce.setType("password");
			}
			else {
				ce.setType(ctfield.getCustomTableFieldDatatype());
			}

			if (ctfield.getCustomTableFieldDatatype().equals("picklist") || ctfield.getCustomTableFieldDatatype().equals("multi-picklist")) {
				ce.setPicklistId(ctfield.getCustomTableFieldPicklistNameId());
			}
			else {
				ce.setPicklistId("");
			}

			ce.setHeader(ctfield.getCustomTableFieldDesc());
			ce.setSearchable(false);
			if (ctfield.isCustomTableFieldDefaultHidden() == null) {
				ce.setHidden(false);
			}
			else {
				ce.setHidden(ctfield.isCustomTableFieldDefaultHidden());
			}
			ce.setValue(ctfield.getCustomTableFieldDefaultValue());
			ce.setRequired(ctfield.isCustomTableFieldRequired());
			ce.setExpression(ctfield.getCustomTableFieldExpression());
			ce.setRegEx(ctfield.getCustomTableFieldRegex());
			ce.setRegExExample(ctfield.getCustomTableFieldRegexExample());
			retList.add(ce);
		}
		return retList;
	}

	public List<Widget> getLoginWidgets(String userName, String passWord) {
		WidgetExample example = new WidgetExample();
		example.createCriteria().andWidgetUsernameEqualTo(userName)
				.andWidgetPasswordEqualTo(passWord)
				.andWidgetTypeEqualTo("customentity")
				.andCustomEntityNameEqualTo("widget_authentication");

		List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
		return widgets;
	}

	public Widget createWidget(String userName, String passWord, String widgettype, String customentitytype) {
		Widget widget = new Widget();
		widget.setWidgetUsername(userName);
		widget.setWidgetPassword(passWord);
		widget.setWidgetType(widgettype);
		widget.setCustomEntityName(customentitytype);
		return widget;
	}

	public Widget getWidget(String guid) {
		WidgetExample example = new WidgetExample();
		example.createCriteria().andWidgetGuidEqualTo(guid);
		List<Widget> widgets = widgetDAO.selectWidgetByExample(example);

		return widgets.get(0);

	}

	public List<Widget> getWidgets(String userName, String passWord, String widgettype, String customentitytype) {
		WidgetExample example = new WidgetExample();
		example.createCriteria().andWidgetUsernameEqualTo(userName)
				.andWidgetPasswordEqualTo(passWord)
				.andWidgetTypeEqualTo(widgettype)
				.andCustomEntityNameEqualTo(customentitytype);

		List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
		return widgets;
	}

	public Widget saveWidget(Widget widget) {
		if (widget.getWidgetId() == 0) {
			widgetDAO.insertWidget(widget);
		}
		else {
			widgetDAO.updateWidgetByPrimaryKey(widget);
		}
		return widget;
	}

	@Override
	public List<Widget> selectWidgetByExample(final WidgetExample example) {
		return widgetDAO.selectWidgetByExample(example);
	}

	@Override
	public Map<String, List<Map<String, Object>>> findPicklistItemsForCustomEntities(final List<CustomEntity> customEntityList, final String guid) {
		final List<Map<String, String>> entityMapList = new ArrayList<Map<String, String>>();

		if (customEntityList != null) {
			for (final CustomEntity customEntity : customEntityList) {
				final Map<String, String> entityMap = new HashMap<String, String>();
				entityMap.put("picklistId", customEntity.getPicklistId());
				entityMap.put("type", customEntity.getType());

				entityMapList.add(entityMap);
			}
		}
		return findPicklistItems(entityMapList, guid);
	}

	@Override
	public Map<String, List<Map<String, Object>>> findPicklistItemsForCustomTableFields(final List<CustomTableField> customTableFieldsList, final String guid) {
		final List<Map<String, String>> entityMapList = new ArrayList<Map<String, String>>();

		if (customTableFieldsList != null) {
			for (final CustomTableField customTableField : customTableFieldsList) {
				final Map<String, String> entityMap = new HashMap<String, String>();
				entityMap.put("picklistId", customTableField.getCustomTableFieldPicklistNameId());
				entityMap.put("type", customTableField.getCustomTableFieldDatatype());

				entityMapList.add(entityMap);
			}
		}
		return findPicklistItems(entityMapList, guid);
	}

	protected Map<String, List<Map<String, Object>>> findPicklistItems(final List<Map<String, String>> entityMapList, final String guid) {
		final Map<String, List<Map<String, Object>>> picklistNameItems = new HashMap<String, List<Map<String, Object>>>();

		if (entityMapList != null) {
			for (final Map<String, String> entityMap : entityMapList) {
				final List<Map<String, Object>> itemsList = new ArrayList<Map<String, Object>>();
				if (StringUtils.isNotBlank(entityMap.get("picklistId")) &&
						("picklist".equalsIgnoreCase(entityMap.get("type")) || "multi-picklist".equalsIgnoreCase(entityMap.get("type")))) {

					final WidgetExample example = new WidgetExample();
					example.createCriteria().andWidgetGuidEqualTo(guid);

					final List<Widget> widgets = selectWidgetByExample(example);

					String wsUsername;
					String wsPassword;

					if ( ! widgets.isEmpty() || guid.equals("")) {
					    if (guid.equals("")) {
					        //
					        // get the authenticated user...
					        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					        wsUsername = auth.getName();
					        wsPassword = (String) auth.getCredentials();
					    }
					    else {

					        Widget widget = widgets.get(0);

					        wsUsername = widget.getWidgetUsername();
					        wsPassword = widget.getWidgetPassword();
					    }
					    List<PicklistItem> picklistItems = null;

						try {
							picklistItems = picklistService.getPickListItems(wsUsername, wsPassword, entityMap.get("picklistId"));
						}
						catch (Exception ex) {
							if (logger.isWarnEnabled()) {
								logger.warn("findPicklistItems: exception occurred = " + ex.toString(), ex);
							}
						}

					    if (picklistItems != null) {
						    final Iterator<PicklistItem> it = picklistItems.iterator();
						    String desc;
						    while (it.hasNext()) {
						        final PicklistItem item = it.next();
						        final Map<String, Object> map = new HashMap<String, Object>();

						        if ( ! item.isInactive()) {
						            map.put("Name", item.getItemName());
						            desc = item.getDefaultDisplayValue();

						            map.put("Description", desc);
						            itemsList.add(map);
						        }
						    }
					    }
					}
					picklistNameItems.put(entityMap.get("picklistId"), itemsList);
				}
			}
		}
		return picklistNameItems;
	}
}