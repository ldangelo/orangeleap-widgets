package com.orangeleap.donatenow.service.impl;

import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap;
import com.orangeleap.client.AbstractCustomizableEntity;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.Filter;
import com.orangeleap.client.GetConstituentByIdRequest;
import com.orangeleap.client.GetConstituentByIdResponse;
import com.orangeleap.client.GetConstituentGiftRequest;
import com.orangeleap.client.GetConstituentGiftResponse;
import com.orangeleap.client.GetCustomTableRowsRequest;
import com.orangeleap.client.GetCustomTableRowsResponse;
import com.orangeleap.client.GetPickListByNameRequest;
import com.orangeleap.client.GetPickListByNameResponse;
import com.orangeleap.client.Gift;
import com.orangeleap.client.ListCustomTablesRequest;
import com.orangeleap.client.ListCustomTablesResponse;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.client.WSClient;
import com.orangeleap.donatenow.dao.CustomEntityWidgetDAO;
import com.orangeleap.donatenow.dao.WidgetDAO;
import com.orangeleap.donatenow.domain.CustomEntityWidget;
import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.domain.WidgetData;
import com.orangeleap.donatenow.domain.WidgetExample;
import com.orangeleap.donatenow.service.OrangeLeapWidgetService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


public class OrangeLeapWidgetServiceImpl implements OrangeLeapWidgetService {
  private static final Log logger = LogFactory.getLog(OrangeLeapWidgetServiceImpl.class);

  @Autowired
  WidgetDAO widgetDAO = null;

  @Autowired
  CustomEntityWidgetDAO customEntityWidgetDAO = null;

  private String customFieldMapValue(CustomFieldMap map,String fieldName) {
        List<Entry> entries = map.getEntry(); 
        Iterator<Entry> itEntries = entries.iterator();
        while (itEntries.hasNext()) {
          Entry entry = itEntries.next();

          if (entry.getKey().equals(fieldName)) return entry.getValue().getValue();
        }
        return null;
  }

  public Long authenticate(String guid, String username, String password) {
    WidgetExample example = new WidgetExample();
    List<Widget> widgets = null;

    example.createCriteria().andWidgetGuidEqualTo(guid);
    widgets = widgetDAO.selectWidgetByExample(example);

    if (widgets == null) {
      return -1L;
    }

    String wsusername = widgets.get(0).getWidgetUsername();
    String wspassword = widgets.get(0).getWidgetPassword();

      WSClient wsClient = null;
      OrangeLeap oleap = null;

      wsClient = new WSClient();
      oleap = wsClient.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),wsusername, wspassword);
      
      GetCustomTableRowsRequest webwidgetRequest = new GetCustomTableRowsRequest();
      GetCustomTableRowsResponse webwidgetResponse = null;

      Filter filter = new Filter();
      filter.setName("username");
      filter.setValue(username);
      
      webwidgetRequest.setTablename("widgetauthentication");
      webwidgetRequest.setOffset(0);
      webwidgetRequest.setLimit(1);
      webwidgetRequest.getFilters().add(filter);

      try {
        webwidgetResponse = oleap.getCustomTableRows(webwidgetRequest);
      } catch (Exception e) {
        logger.error("Errror: " + e.getMessage());
        return -1L;
      }


      
      if (webwidgetResponse != null) {
        CustomTableRow row = webwidgetResponse.getCustomTableRow().get(0);
        String val = customFieldMapValue(row.getCustomFieldMap(),"password");
        if (val.equals(password)) {
          return Long.parseLong(customFieldMapValue(row.getCustomFieldMap(),"constituent"));
        }
      }

    
    return -1L;
  }

  public WidgetData create() {
    return new WidgetData();
  }
  public Widget selectWidgetById(Long id) {
    return widgetDAO.selectWidgetByPrimaryKey(id);
  }

  public Constituent getConstituent(String guid, Long id) {
    WidgetExample example = new WidgetExample();
    example.createCriteria().andWidgetGuidEqualTo(guid);
    example.createCriteria().andWidgetTypeEqualTo("customentity");
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

      GetConstituentByIdRequest request = new GetConstituentByIdRequest();
      GetConstituentByIdResponse response = null;

      request.setId(id);

      response = oleap.getConstituentById(request);
      if (response != null) {
        return response.getConstituent();
      }

    }
    return null;

  }

  public List<Gift> getConstituentGifts(String guid, Long constituentId) {
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

    GetConstituentGiftRequest request = new GetConstituentGiftRequest();
    GetConstituentGiftResponse response = null;
    
    request.setConstituentId(constituentId);
    request.setOffset(0);
    request.setLimit(99);

    response = oleap.getConstituentGift(request);

    if (response != null) {
      return response.getGift();
    }

    }
    return null;
  }

  public List<PicklistItem> getPickListItems(String guid,String picklistname) {
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
    GetPickListByNameRequest request = new GetPickListByNameRequest();
    GetPickListByNameResponse response = null;

    request.setName(picklistname);
    response = oleap.getPickListByName(request);
    if (response != null) {
      return response.getPicklist().getPicklistItems();
    }

    }
    return null;
  }

  public CustomTable getCustomEntity(String name) {
        WidgetExample example = new WidgetExample();
    example.createCriteria().andWidgetGuidEqualTo(name);
    example.createCriteria().andWidgetTypeEqualTo("customentity");
    List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
    
    if (widgets.size() > 0) {

      //
      // guid is a unique key so this will only return one widget
      Widget widget = widgets.get(0);
    
      CustomEntityWidget ceWidget = null;
      ceWidget = customEntityWidgetDAO.selectCustomEntityWidgetByPrimaryKey(widget.getWidgetId());

    String wsusername = widgets.get(0).getWidgetUsername();
    String wspassword = widgets.get(0).getWidgetPassword();

      WSClient wsClient = null;
      OrangeLeap oleap = null;

      wsClient = new WSClient();
      oleap = wsClient.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),wsusername, wspassword);
      
      ListCustomTablesRequest request = new ListCustomTablesRequest();
      ListCustomTablesResponse response = null;

      response = oleap.listCustomTables(request);

      Iterator<CustomTable> it = response.getCustomTable().iterator();
      while (it.hasNext()) {
        CustomTable table = it.next();
        if (table.getCustomTableName().equals(ceWidget.getCustomEntityName())) {
          return table;
        }

      }

      
    }
    return null;
  }

  public WidgetData process(WidgetData data) {
    return data;
  }

  public List<Widget> listWidgets(String userName, String password) {
    WidgetExample example = new WidgetExample();

    example.createCriteria().andWidgetUsernameEqualTo(userName);
    example.createCriteria().andWidgetPasswordEqualTo(password);

    return widgetDAO.selectWidgetByExample(example);
  }

  public void updateViewCount(String guid, String refererrer)
  {
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
	if (widget.getWidgetCreateDate() == null)
	    widget.setWidgetCreateDate(new Date());
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	String userName = auth.getName();
	String password = (String) auth.getCredentials();
	String siteName = userName.substring(userName.indexOf('@') + 1);
	widget.setWidgetUsername(userName);
	widget.setWidgetPassword(password);

	
	Widget result = null;
	
	if (widget.getWidgetId() != null)
		widgetDAO.updateWidgetByPrimaryKey(widget);
	else
		widgetDAO.insertWidget(widget);
	
    return widget;
  }
}