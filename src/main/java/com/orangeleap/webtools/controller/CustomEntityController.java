package com.orangeleap.webtools.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.CustomField;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.CustomTableField;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.webtools.domain.CustomEntity;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.service.OrangeLeapClientService;
import com.orangeleap.webtools.service.PicklistService;
import com.orangeleap.webtools.service.WidgetService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class CustomEntityController extends MultiActionController {

	@Autowired
	WidgetService widgetService;

	@Autowired
	OrangeLeapClientService orangeLeapClientService;

	@Autowired
	PicklistService picklistService;

	@Resource(name = "sessionCache")
	Cache sessionCache;
	
	public final static String CONSTITUENT_ID = "constituentId";
	public final static String CONSTITUENT_DOT_ID = "constituent.id";
	
	@SuppressWarnings("unchecked")
	public ModelAndView view(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String guid = request.getParameter("guid");
		String sessionId = request.getParameter("sessionId");
		Long constituentid = - 1L;

		final Widget w = widgetService.getWidget(guid);
		if (w == null) {
			return getModelMapError("Invalid guid");
		}

		if (w.getWidgetAuthenticationRequired() || !sessionId.isEmpty()) {
			//
			// get the constituent id out of the session cache
			Element elem = sessionCache.get(sessionId);
			if (elem == null) {
				resetSessionCookie(request, response);
			
				return getModelMapError("Invalid Session ID");
			}
			else {
				constituentid = (Long) elem.getObjectValue();
			}
		}

		if (guid != null && ! guid.equals("undefined")) {
			final List<CustomEntity> ceList = widgetService.getCustomEntity(guid);

			return getModelMap(ceList, guid, constituentid, w);
		}

		return getModelMapError("Error trying to retrieve customEntity");
	}

	private void resetSessionCookie(final HttpServletRequest request, final HttpServletResponse response) {
		Cookie sessionCookie = null;
		final Cookie sessionCookies[] = request.getCookies();

		for (final Cookie aCookie : sessionCookies) {
			if (aCookie.getName().equals("sessionId")) {
				sessionCookie = aCookie;
				sessionCookie.setMaxAge(0);
				sessionCookie.setValue("");
				break;
			}
		}
		if (sessionCookie == null) {
			//
			// clear the
			sessionCookie = new Cookie("sessionId", "");
		}

		response.addCookie(sessionCookie);
	}

	/**
	 * Generates modelMap to return in the modelAndView
	 *
	 * @param row
	 * @return
	 */
	private ModelAndView getModelMap(CustomTableRow row) {
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", 1);
		modelMap.put("data", row);
		modelMap.put("success", true);

		return new ModelAndView("jsonView", modelMap);
	}

	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String guid = request.getParameter("guid");
		String sessionId = request.getParameter("sessionId");
		Long constituentid = - 1L;


		//
		// incase a successurl is set and we are going to redirect
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));


		Widget w = widgetService.getWidget(guid);
		if (w == null) {
			return getModelMapError("Invalid guid");
		}

		if (w.getWidgetAuthenticationRequired()) {
			//
			// get the constituent id out of the session cache
			Element elem = sessionCache.get(sessionId);
			if (elem == null) {
				Cookie sessionCookie = null;
				Cookie sessionCookies[] = request.getCookies();
				for (int i = 0; i < sessionCookies.length; i++) {
					if (sessionCookies[i].getName().equals("sessionId")) {
						sessionCookie = sessionCookies[i];
						sessionCookie.setMaxAge(0);
						sessionCookie.setValue("");
						break;
					}
				}
				
				if (sessionCookie == null) {
					//
					// clear the 
					sessionCookie = new Cookie("sessionId", "");
				}
				
				response.addCookie(sessionCookie);
			}
			else {
				constituentid = (Long) elem.getObjectValue();
			}
		} else {
			//
			// get the constituent id out of the session cache if it's there
			Element elem = sessionCache.get(sessionId);
			if (elem != null) {
				constituentid = (Long) elem.getObjectValue();
			}			
		}

		if (constituentid != null && ! constituentid.equals("undefined")
				&& ! constituentid.equals("")) {
			orangeLeapClientService.removeFromCache(guid, new Long(constituentid));
		}

		if (guid != null && ! guid.equals("undefined")) {
			try {
				CustomTableRow row = widgetService.CreateCustomTableRow(guid,
						request);
				if (row != null) {
					com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry entry = new com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry();
					entry.setKey("customtablerowid");
					CustomField customField = new CustomField();
					customField.setValue(row.getId().toString());
					entry.setValue(customField);
					row.getCustomFieldMap().getEntry().add(entry);
					return getModelMap(row);
				}
			}
			catch (Exception e) {
				return getModelMapError(e.getMessage());
			}
		}


		return getModelMapError("Error trying to create customEntity.");
	}

	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getModelMapError("Error trying to update customEntity");
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getModelMapError("Error trying to delete customEntity");
	}

	private String getCustomFieldMapValue(CustomFieldMap cfMap,String index) {
		List<com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry> list = cfMap.getEntry();
		Iterator<com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry> it = list.iterator();
		
		while (it.hasNext()) {
			com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry entry = it.next();
			if (entry.getKey().equals(index)) {
				return entry.getValue().getValue();
			}
		}
		return "";
	}
	private ModelAndView getModelMap(final List<CustomEntity> ceList, final String guid,
			final Long constituentid, final Widget widget) {
		final List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		final Map<String, Object> modelMap = new HashMap<String, Object>();
		final Map<String, Object> metaData = new HashMap<String, Object>();

		metaData.put("idProperty", "id");
		metaData.put("root", "rows");
		metaData.put("totalProperty", "totalRows");
		metaData.put("successProperty", "success");
		metaData.put("fields", ceList);
		final Map<String, List<Map<String, Object>>> picklistNameItems = widgetService.findPicklistItemsForCustomEntities(ceList, guid);
		metaData.put("picklistNameItems", picklistNameItems);

		metaData.put("allowLogout", widget.getWidgetAuthenticationRequired());
		metaData.put("allowLogoutReset", "online_sponsorship".equalsIgnoreCase(widget.getCustomEntityName()) ||
				"online_donation".equalsIgnoreCase(widget.getCustomEntityName()));

		modelMap.put("metaData", metaData);
		modelMap.put("success", true);

		Constituent constituent = null;
		long rowId = 0;
		if (constituentid != - 1) {
			//
			// get the constituent associated with this custom entity
			constituent = orangeLeapClientService.getConstituentById(guid, constituentid);
			//determine if there is only one custom entity record(ie this table has a unique one to one record with the constituent)
			//if so locate the custom table row(there will only be one) and use that id
			final CustomTable table = widgetService.getCustomTable(guid);
			boolean ceReferencesConstituent = false;
			boolean forConstituentContext = false;
			boolean hasConstituentIdKey = false;
			String constituentIdFieldName = null;
			int constituentIdFieldCount = 0;
			int keys = 0;
			//we need to find the constituent id field, try to use the expression if it's not found or finds more than one use the first occurrence
			for (final CustomTableField field : table.getFields()) {
				if (field.getCustomTableFieldExpression().equalsIgnoreCase(CONSTITUENT_DOT_ID)){
					if (constituentIdFieldCount == 0){
						constituentIdFieldName = field.getCustomTableFieldName();
						constituentIdFieldCount ++;
					}
				}				
			}
			if (constituentIdFieldName == null){ 
				constituentIdFieldName = CONSTITUENT_ID;// if it wasn't found with the expression above use the standard field name
			}
			// for more info see com.orangeleap.tangerine.service.customization.CustomTableMaintenanceServiceImpl.forConstituentAttributes(CustomTable)
			for (final CustomTableField field : table.getFields()) {
				if (field.isCustomTableFieldTakeConstituentContext()) {
					forConstituentContext = true;
				}
				if (field.getCustomTableFieldName().equalsIgnoreCase(constituentIdFieldName) && field.isCustomTableFieldKey()) {
					hasConstituentIdKey = true;
				}
				if (field.isCustomTableFieldKey()) {
					keys++;
				}
				
			}
			ceReferencesConstituent = forConstituentContext && hasConstituentIdKey && keys == 1;
			//if there is a unique one to one record with the constituent then we need to locate 
			//the custom table row id for that record
			if (ceReferencesConstituent){
				final Map<String, String> whereFieldEqualsValue = new HashMap<String, String>();
				whereFieldEqualsValue.put(constituentIdFieldName, constituentid.toString());
				
				try {
					List<CustomTableRow> rows = widgetService.getCustomTableRows(guid, whereFieldEqualsValue);
					//if a row was returned
					if (rows != null && rows.size() == 1){
						rowId = rows.get(0).getId();
					}
					else if (rows != null && rows.size() == 0){
						//set rowId to zero so it will create a new row
						rowId = 0;
					}
					else {
						//TODO throw error as the row count should be 0 or 1
					}
				}
				catch (Exception e) {
						return getModelMapError(e.getMessage());
				}
				
			}
			
			
		}

		//
		// add a row into the modelMap
		final Iterator<CustomEntity> it = ceList.iterator();
		final Map<String, Object> row = new HashMap<String, Object>();
		row.put("id", rowId);
		row.put("customtablerowid", rowId);
		while (it.hasNext()) {
			final CustomEntity ce = it.next();

			//
			// skip the id as we already put it in...
			if (ce.getName().equals("id") || ce.getName().equals("customtablerowid")) {
				continue;
			}

			try {
				String ceName = ce.getName();

				if (constituent != null) {
					if (ce.getExpression().startsWith("constituent") || ce.getType().equals("entity-reference")) {
						//
						// split the string on instances of '.'
						String args[] = ce.getExpression().split("\\.", - 1);
						if (args.length == 2 || ce.getType().equals("entity-reference")) {
							if (ce.getType().equals("entity-reference")) {
								if (ce.getReferencedEntityType().equals("address")) {
									List<Map<String, Object>> addressList = new ArrayList<Map<String, Object>>();
									ArrayList<com.orangeleap.client.Address> addresses = (ArrayList) PropertyUtils.getSimpleProperty(constituent, "addresses");

									Map<String, Object> blankAddressMap = new HashMap<String, Object>();
									blankAddressMap.put("Name", "0");
									blankAddressMap.put("Description", "Create New");
									addressList.add(blankAddressMap);
									
									for (com.orangeleap.client.Address address : addresses) {
										if (address.isPrimary()) {
											row.put(ce.getName(), address.getId().toString());
										}
										StringBuilder shortDisplay = new StringBuilder();
							            shortDisplay.append(org.apache.commons.lang.StringUtils.substring(address.getAddressLine1(), 0, 10));
								        shortDisplay.append(" ... ");
								        if (org.apache.commons.lang.StringUtils.isNotEmpty(address.getPostalCode())) {
									        shortDisplay.append(org.apache.commons.lang.StringUtils.substring(address.getPostalCode(), 0, 5));
								        }
								        else {
									        shortDisplay.append(address.getCountry());
								        }
								        Map<String, Object> addressMap = new HashMap<String, Object>();
								        addressMap.put("Name", address.getId().toString());
								        addressMap.put("Description", shortDisplay.toString());
								        addressList.add(addressMap);
									}
									
									if (addresses.size() == 0) {
										row.put(ce.getName(), "0");
									}

									metaData.put("addresses", addressList);
								} else if (ce.getReferencedEntityType().equals("phone")) {
									List<Map<String, Object>> phoneList = new ArrayList<Map<String, Object>>();
									ArrayList<com.orangeleap.client.Phone> phones = (ArrayList) PropertyUtils.getSimpleProperty(constituent, "phones");

									Map<String, Object> blankPhoneMap = new HashMap<String, Object>();
									blankPhoneMap.put("Name", "0");
									blankPhoneMap.put("Description", "Create New");
									phoneList.add(blankPhoneMap);
									
									for (com.orangeleap.client.Phone phone : phones) {
										if (phone.isPrimary()) {
											row.put(ce.getName(), phone.getId().toString());
										}
								        Map<String, Object> phoneMap = new HashMap<String, Object>();
								        phoneMap.put("Name", phone.getId().toString());
								        phoneMap.put("Description", phone.getNumber());
								        phoneList.add(phoneMap);
									}

									if (phones.size() == 0) {
										row.put(ce.getName(), "0");
									}
									
									metaData.put("phones", phoneList);
								} else if (ce.getReferencedEntityType().equals("email")) {
									List<Map<String, Object>> emailList = new ArrayList<Map<String, Object>>();
									ArrayList<com.orangeleap.client.Email> emails = (ArrayList) PropertyUtils.getSimpleProperty(constituent, "emails");

									Map<String, Object> blankEmailMap = new HashMap<String, Object>();
									blankEmailMap.put("Name", "0");
									blankEmailMap.put("Description", "Create New");
									emailList.add(blankEmailMap);
									
									for (com.orangeleap.client.Email email : emails) {
										if (email.isPrimary()) {
											row.put(ce.getName(), email.getId().toString());
										}
								        Map<String, Object> emailMap = new HashMap<String, Object>();
								        emailMap.put("Name", email.getId().toString());
								        emailMap.put("Description", email.getEmailAddress());
								        emailList.add(emailMap);
									}
									
									if (emails.size() == 0) {
										row.put(ce.getName(), "0");
									}
									
									metaData.put("emails", emailList);
								} else {
									Object property = PropertyUtils.getSimpleProperty(constituent, args[1]);
									row.put(ce.getName(), property);
								}
							} else {
								Object property = PropertyUtils.getSimpleProperty(constituent, args[1]);
								row.put(ce.getName(), property);
							}
						}
						else { 
							String property = ce.getExpression().substring(ce.getExpression().indexOf(".") + 1);
							
							if (property.contains("customFieldMap")) {
								String strcf = property.substring(0,property.indexOf('['));
								CustomFieldMap cfMap = (CustomFieldMap) PropertyUtils.getNestedProperty(constituent, strcf);
								
								String index = property.substring(property.indexOf('[') + 1, property.indexOf(']'));
								final String value = getCustomFieldMapValue(cfMap, index);
								row.put(ce.getName(), value);
							}
							else {
								final Object value = PropertyUtils.getNestedProperty(constituent, property);
								row.put(ce.getName(), value);
							}
						}
					} else {
						row.put(ce.getName(), ce.getValue());
					}
				}
				else {
					// we don't have a constituent
					if (ce.getType().equals("entity-reference")) {
						List<Map<String, Object>> blankList = new ArrayList<Map<String, Object>>();
						
						// Create blank address/email/phone list
						Map<String, Object> blankMap = new HashMap<String, Object>();
						blankMap.put("Name", "0");
						blankMap.put("Description", "Create New");
						blankList.add(blankMap);

						if (ce.getReferencedEntityType().equals("address")) {
							metaData.put("addresses", blankList);
							// Default to create new
							row.put(ce.getName(), 0);
							// No addresses, just hide the addresses drop down
							List<CustomEntity> customEntityList = (List<CustomEntity>) metaData.get("fields");
							CustomEntity customEntity = customEntityList.get(customEntityList.indexOf(ce));
							customEntity.setHidden(true);
						} else if (ce.getReferencedEntityType().equals("email")) {
							metaData.put("phones", blankList);
							// Default to create new
							row.put(ce.getName(), 0);						
							// No phones, just hide the addresses drop down
							List<CustomEntity> customEntityList = (List<CustomEntity>) metaData.get("fields");
							CustomEntity customEntity = customEntityList.get(customEntityList.indexOf(ce));
							customEntity.setHidden(true);
						} else if (ce.getReferencedEntityType().equals("phone")) {
							metaData.put("emails", blankList);
							// Default to create new
							row.put(ce.getName(), 0);						
							// No emails, just hide the addresses drop down
							List<CustomEntity> customEntityList = (List<CustomEntity>) metaData.get("fields");
							CustomEntity customEntity = customEntityList.get(customEntityList.indexOf(ce));
							customEntity.setHidden(true);
						}
					} else {
						row.put(ce.getName(), ce.getValue());
					}
				}
			}
			catch (Exception e) {
				row.put(ce.getName(), ce.getValue());
			}
		}
		returnList.add(row);
		modelMap.put("rows", returnList);
		modelMap.put("totalRows", returnList.size());

		return new ModelAndView("jsonView", modelMap);
	}

	/**
	 * Generates modelMap to return in the modelAndView in case of exception
	 *
	 * @param msg message
	 * @return
	 */
	private ModelAndView getModelMapError(String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return new ModelAndView("jsonView", modelMap);
	}

	public void logout(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		final String guid = request.getParameter("guid");
		final String sessionId = request.getParameter("sessionId");

		if (StringUtils.isNotBlank(sessionId)) {
			resetSessionCookie(request, response);
			sessionCache.remove(sessionId);
		}

		final Widget widget = widgetService.getWidget(guid);
		final String authenticationUrl = widget.getWidgetAuthenticationURL();

//		request.getSession().invalidate();
		response.sendRedirect(authenticationUrl);
	}
}