package com.orangeleap.donatenow.controller.json;

import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap;
import com.orangeleap.client.AbstractCustomizableEntity;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.CustomTableField;
import com.orangeleap.client.GetConstituentGiftRequest;
import com.orangeleap.client.GetConstituentGiftResponse;
import com.orangeleap.client.GetCustomTableRowsRequest;
import com.orangeleap.client.GetCustomTableRowsResponse;
import com.orangeleap.client.Gift;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.ReadCustomTableByNameRequest;
import com.orangeleap.client.ReadCustomTableByNameResponse;
import com.orangeleap.client.WSClient;
import com.orangeleap.donatenow.dao.CustomEntityWidgetDAO;
import com.orangeleap.donatenow.dao.WidgetDAO;
import com.orangeleap.donatenow.domain.CustomEntityWidget;
import com.orangeleap.donatenow.domain.Placements;
import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.domain.WidgetExample;
import com.orangeleap.donatenow.service.PlacementsService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customEntityList.json")
public class CustomEntityListController {
    @Autowired
    WidgetDAO widgetDAO = null;

  @Autowired
  CustomEntityWidgetDAO customEntityWidgetDAO = null;

	@RequestMapping(method = RequestMethod.GET)
   public void  getCustomEntityList(@RequestParam(required=true) String guid, ModelMap modelMap) {
      List <Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();

    WidgetExample example = new WidgetExample();
    example.createCriteria().andWidgetGuidEqualTo(guid);
    
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
      //
      // first get the table definition
      ReadCustomTableByNameRequest request = new ReadCustomTableByNameRequest();
      ReadCustomTableByNameResponse response = null;
      request.setName(ceWidget.getCustomEntityName());

      response = oleap.readCustomTableByName(request);

      //
      // now get the rows....
      GetCustomTableRowsRequest rowRequest = new GetCustomTableRowsRequest();
      GetCustomTableRowsResponse rowResponse = null;
      
      rowRequest.setTablename(ceWidget.getCustomEntityName());
      rowResponse = oleap.getCustomTableRows(rowRequest);

        if (rowResponse != null) {
          populateMetaData(response.getCustomTable(),modelMap);

          addCustomTableRows(rowResponse.getCustomTableRow(),returnList);
        }
    }

      modelMap.put("rows", returnList);
      modelMap.put("totalRows", returnList.size());
    }

  @RequestMapping(method = RequestMethod.POST)
  public void postCustomEntityList(@RequestParam(required=true) String guid, 
      @RequestParam(required=true) Long start,
      @RequestParam(required=true) Long limit,
      @RequestParam(required=true) String pattern,ModelMap modelMap) {
      List <Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();

    WidgetExample example = new WidgetExample();
    example.createCriteria().andWidgetGuidEqualTo(guid);
    
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
      //
      // first get the table definition
      ReadCustomTableByNameRequest request = new ReadCustomTableByNameRequest();
      ReadCustomTableByNameResponse response = null;
      request.setName(ceWidget.getCustomEntityName());

      response = oleap.readCustomTableByName(request);

      //
      // now get the rows....
      GetCustomTableRowsRequest rowRequest = new GetCustomTableRowsRequest();
      GetCustomTableRowsResponse rowResponse = null;
      
      rowRequest.setTablename(ceWidget.getCustomEntityName());
      rowRequest.setOffset(start);
      rowRequest.setLimit(limit);
      rowResponse = oleap.getCustomTableRows(rowRequest);

        if (rowResponse != null) {
          populateMetaData(response.getCustomTable(),modelMap);

          addCustomTableRows(rowResponse.getCustomTableRow(),returnList);
        }
    }
    
    modelMap.put("rows", returnList);
    modelMap.put("totalRows", returnList.size());
  }

  private void populateMetaDataFields(CustomTable table,Map<String,Object> metaData) {
    List<Map<String,Object>> fields = new ArrayList<Map<String,Object>>();    
      
      List<CustomTableField> ctfields = table.getFields();
      Iterator<CustomTableField> ctit = ctfields.iterator();

      while (ctit.hasNext()) {
        CustomTableField ctfield = ctit.next();
        
        //
        // don't list section definitions or items with a '.' in their name...
        // only list item's that are marked as 'listable'...
        if (ctfield.getCustomTableFieldDatatype().compareTo("section") == 0 
            || ctfield.getCustomTableFieldName().contains(".")
            || ctfield.isCustomTableFieldListView() == false) {
            continue;
          }

          Map<String,Object> map = new HashMap<String,Object>();
          map.put("name",ctfield.getCustomTableFieldName());
          map.put("header",ctfield.getCustomTableFieldDesc());
          fields.add(map);

      }
      
      metaData.put("fields",fields);
  }

  private void populateMetaData(CustomTable table,ModelMap modelMap) { 
      Map<String, Object> metaData = new HashMap<String, Object>();    
      metaData.put("idProperty","0");
      metaData.put("root","rows");
      metaData.put("totalProperty","totalRows");
      //    Map<String,Object> sortInfo = new HashMap<String,Object>();
      //      sortInfo.put("field","id");
      //      sortInfo.put("direction","ASC");
      //      metaData.put("sortInfo",sortInfo);
      metaData.put("successProperty","success");
      metaData.put("success",true);
      populateMetaDataFields(table,metaData);
      //      metaData.put("start",0);
      //      metaData.put("limit",20);
      modelMap.put("metaData",metaData);
  }

  private void addCustomTableRows(List<CustomTableRow> rows, List<Map<String,Object>> returnList) {
    Iterator<CustomTableRow> it = rows.iterator();

    int id = 0;
    while (it.hasNext()) {
      CustomTableRow row = it.next();
      Map<String, Object> map = new HashMap<String, Object>();
      List<Entry> fields = row.getCustomFieldMap().getEntry();
      Iterator<Entry> fldIt = fields.iterator();
      map.put("id",id++);
      while (fldIt.hasNext()) {
        Entry entry = fldIt.next();
        map.put(entry.getKey(),entry.getValue().getValue());
      }
      returnList.add(map);
    }

  }

   
}
