package com.orangeleap.donatenow.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap;
import com.orangeleap.client.AbstractCustomizableEntity;
import com.orangeleap.client.CustomField;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.CustomTableField;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.GetConstituentGiftRequest;
import com.orangeleap.client.GetConstituentGiftResponse;
import com.orangeleap.client.GetCustomTableRowsRequest;
import com.orangeleap.client.GetCustomTableRowsResponse;
import com.orangeleap.client.SaveOrUpdateCustomTableRowRequest;
import com.orangeleap.client.SaveOrUpdateCustomTableRowResponse;
import com.orangeleap.client.Gift;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.client.ReadCustomTableByNameRequest;
import com.orangeleap.client.ReadCustomTableByNameResponse;
import com.orangeleap.client.WSClient;
import com.orangeleap.donatenow.service.CustomEntityService;
import com.orangeleap.donatenow.domain.CustomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.domain.CustomEntityWidget;
import com.orangeleap.donatenow.domain.WidgetExample;
import com.orangeleap.donatenow.domain.CustomEntityWidgetExample;
import com.orangeleap.donatenow.dao.CustomEntityWidgetDAO;
import com.orangeleap.donatenow.dao.WidgetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import com.orangeleap.donatenow.service.PicklistService;
import javax.servlet.http.HttpServletRequest;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import javax.annotation.Resource;
import javax.xml.rpc.soap.SOAPFaultException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service("customEntityService")
public class CustomEntityServiceImpl implements CustomEntityService {
    private static final Log logger = LogFactory.getLog(CustomEntityServiceImpl.class);

  @Autowired
   private  WidgetDAO widgetDAO = null;

  @Autowired
  private  CustomEntityWidgetDAO customEntityWidgetDAO = null;

  @Autowired
  private  PicklistService picklistService;

  @Resource(name="customTableCache")
  Cache customTableCache;

  public CustomTableRow CreateCustomTableRow(String guid, HttpServletRequest request) {
    List<CustomEntity> ceList = getCustomEntity(guid);
    Iterator<CustomEntity> it = ceList.iterator();

    CustomTableRow row = new CustomTableRow();
    CustomFieldMap customFieldMap = new CustomFieldMap();
    row.setCustomFieldMap(customFieldMap);

    while (it.hasNext()) {
      CustomEntity ce = it.next();
      Entry entry = new Entry();

      if (ce.getType().equals("section")) continue;
      CustomField val = new CustomField();
      val.setName(ce.getName());
      val.setValue(request.getParameter(ce.getName()));
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
      oleap = wsClient.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),wsusername, wspassword);
      
      
      row.setCustomTableId(table.getId());
      row.setCustomTableRowActive(true);

      SaveOrUpdateCustomTableRowRequest rowrequest = new SaveOrUpdateCustomTableRowRequest();
      SaveOrUpdateCustomTableRowResponse rowresponse = null;
      rowrequest.setCustomTableRow(row);
      try {
        rowresponse = oleap.saveOrUpdateCustomTableRow(rowrequest);
      } catch (Exception e ) {
        if (e instanceof SOAPFaultException) {
				SOAPFaultException sfe = (SOAPFaultException)e;
				Throwable t = ((SOAPFaultException)e).getCause();
                Detail d = sfe.getDetail();
                Iterator<DetailEntry> dit = d.getDetailEntries();
                while (dit.hasNext()) {
                  DetailEntry dte = dit.next();
                  logger.error(dte.getTextContent());
                }
			}

      }


      if (rowresponse != null) {
        return rowresponse.getCustomTableRow();
      }

    }
    
    return null;
  }

    private CustomTable getCustomTable(String guid) {
      WidgetExample example = new WidgetExample();
      example.createCriteria().andWidgetGuidEqualTo(guid);
      
      List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
      
    if (widgets.size() > 0) {

      //
      // guid is a unique key so this will only return one widget
      Widget widget = widgets.get(0);
      CustomEntityWidget ceWidget = null;
      ceWidget = customEntityWidgetDAO.selectCustomEntityWidgetByPrimaryKey(widget.getWidgetId());
      Cache cache = (Cache) customTableCache;
      Element elem = cache.get(ceWidget.getCustomEntityName());
      if (elem != null) {
        return (CustomTable) elem.getObjectValue();
      }


      String wsusername = widgets.get(0).getWidgetUsername();
      String wspassword = widgets.get(0).getWidgetPassword();
      
      WSClient wsClient = null;
      OrangeLeap oleap = null;
      
      wsClient = new WSClient();
      oleap = wsClient.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),wsusername, wspassword);
      //
      // first get the table definition
      ReadCustomTableByNameRequest request = new ReadCustomTableByNameRequest();
      ReadCustomTableByNameResponse response = null;
      request.setName(ceWidget.getCustomEntityName());

      response = oleap.readCustomTableByName(request);

      if (response != null) {
        cache.put(new Element(response.getCustomTable().getCustomTableName(),response.getCustomTable()));
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

  private List<CustomEntity> generateCustomEntities(CustomTable table)
  {
  
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
      ce.setType(ctfield.getCustomTableFieldDatatype());
      if (ctfield.getCustomTableFieldDatatype().equals("picklist")) {
        ce.setPicklistId(ctfield.getCustomTableFieldPicklistNameId());
      } else {
        ce.setPicklistId("");
      }


      ce.setHeader(ctfield.getCustomTableFieldDesc());
      ce.setSearchable(false);
      ce.setHidden(false);
      ce.setValue("");
      ce.setRequired(ctfield.isCustomTableFieldRequired());
      ce.setExpression(ctfield.getCustomTableFieldExpression());
      retList.add(ce);
    }
    return retList;
  }
}