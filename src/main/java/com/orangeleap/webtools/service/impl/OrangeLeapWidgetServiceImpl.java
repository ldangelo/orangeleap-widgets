package com.orangeleap.webtools.service.impl;

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
import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetData;
import com.orangeleap.webtools.domain.WidgetExample;
import com.orangeleap.webtools.service.OrangeLeapWidgetService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.orangeleap.webtools.service.PlacementsService;
import com.orangeleap.webtools.service.OrangeLeapClientService;
public class OrangeLeapWidgetServiceImpl implements OrangeLeapWidgetService {
  private static final Log logger = LogFactory.getLog(OrangeLeapWidgetServiceImpl.class);

  @Autowired
  WidgetDAO widgetDAO = null;

  @Autowired
  PlacementsService placementsService;

  @Autowired
  OrangeLeapClientService orangeLeapClientService;

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
      oleap = wsClient.getOrangeLeap(System.getProperty("webtools.wsdllocation"),wsusername, wspassword);
      
      GetCustomTableRowsRequest webwidgetRequest = new GetCustomTableRowsRequest();
      GetCustomTableRowsResponse webwidgetResponse = null;

      Filter filter = new Filter();
      filter.setName("user_name");
      filter.setValue(username);
      
      webwidgetRequest.setTablename("widget_authentication");
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
        if (webwidgetResponse.getCustomTableRow().size() > 0) {
          CustomTableRow row = webwidgetResponse.getCustomTableRow().get(0);
          String val = customFieldMapValue(row.getCustomFieldMap(),"user_password");
        if (val != null && val.equals(password)) {
          return Long.parseLong(customFieldMapValue(row.getCustomFieldMap(),"constituent_id"));
        }
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
    return  orangeLeapClientService.getConstituentById(guid, id);
  }

  public List<Gift> getConstituentGifts(String guid, Long constituentId) {
      return orangeLeapClientService.getConstituentGifts(guid, constituentId);
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

    placementsService.updateViewCount(widget,refererrer);
  }

  public void updateErrorCount(String guid, String refererrer)
  {
    WidgetExample example = new WidgetExample();
    example.createCriteria().andWidgetGuidEqualTo(guid);
    List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
    
    //
    // guid is a unique key so this will only return one widget
    Widget widget = widgets.get(0);

    widget.setWidgetErrorCount(widget.getWidgetErrorCount() + 1);
    widgetDAO.updateWidgetByPrimaryKey(widget);

    placementsService.updateErrorCount(widget,refererrer);
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

  public CustomTableRow addCustomTableRow(String guid,Entry[] row)
  {
    logger.error(row.toString());
    return null;
  }
}