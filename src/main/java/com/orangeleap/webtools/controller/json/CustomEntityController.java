package com.orangeleap.webtools.controller.json;

import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap;
import com.orangeleap.client.AbstractCustomizableEntity;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.CustomTableField;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.GetConstituentGiftRequest;
import com.orangeleap.client.GetConstituentGiftResponse;
import com.orangeleap.client.GetCustomTableRowsRequest;
import com.orangeleap.client.GetCustomTableRowsResponse;
import com.orangeleap.client.Gift;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.client.ReadCustomTableByNameRequest;
import com.orangeleap.client.ReadCustomTableByNameResponse;
import com.orangeleap.client.WSClient;
import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Placements;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetExample;
import com.orangeleap.webtools.service.PicklistService;
import com.orangeleap.webtools.service.PlacementsService;
import com.orangeleap.webtools.service.WidgetService;

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

//@Controller
//@RequestMapping("/customEntity.json")
public class CustomEntityController {
    @Autowired
    WidgetDAO widgetDAO = null;


  @Autowired
  PicklistService picklistService;

  @Autowired
  WidgetService widgetService;
  
  Map fieldMap = new HashMap<String,CustomTableField>();
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


      CustomTable table = widgetService.getCustomTable(widget.getWidgetGuid());
      populateMetaData(table,modelMap);



    }

      modelMap.put("rows", returnList);
      modelMap.put("totalRows", returnList.size());
    }

  @RequestMapping(method = RequestMethod.POST)
  public void postCustomEntityt(@RequestParam(required=true) String guid,ModelMap modelMap) {
      List <Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();

    WidgetExample example = new WidgetExample();
    example.createCriteria().andWidgetGuidEqualTo(guid);
    
    List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
    
    if (widgets.size() > 0) {

      //
      // guid is a unique key so this will only return one widget
      Widget widget = widgets.get(0);


      CustomTable table = widgetService.getCustomTable(widget.getWidgetGuid());
      populateMetaData(table,modelMap);

  }
    
    modelMap.put("rows", returnList);
    modelMap.put("totalRows", returnList.size());
  }

  private void populateMetaDataFields(CustomTable table,Map<String,Object> metaData) {
    List<Map<String,Object>> fields = new ArrayList<Map<String,Object>>();    
      
      List<CustomTableField> ctfields = table.getFields();
      Iterator<CustomTableField> ctit = ctfields.iterator();

      //
      // add the id
      Map<String,Object> map = new HashMap<String,Object>();
      map.put("name","id");
      map.put("type","text");
      map.put("header","Id");
      map.put("searchable",false);
      map.put("hidden",true);
      map.put("required",false);
      map.put("regex", "");
      map.put("regexText", "");
      fields.add(map);

      while (ctit.hasNext()) {
        CustomTableField ctfield = ctit.next();
        fieldMap.put(ctfield.getCustomTableFieldName(),ctfield);

        //
        // don't list section definitions or items with a '.' in their name...
        // only list item's that are marked as 'Include in web widgets'
        if (ctfield.getCustomTableFieldName().contains(".")
            || !(ctfield.isCustomTableFieldWWViewable())) {
            continue;
          }

          map = new HashMap<String,Object>();
          map.put("name",ctfield.getCustomTableFieldName());
          map.put("type",ctfield.getCustomTableFieldDatatype());
          map.put("header",ctfield.getCustomTableFieldDesc());
          map.put("searchable",ctfield.isCustomTableFieldSearchable());
          map.put("hidden",false);
          map.put("required",ctfield.isCustomTableFieldRequired());
          map.put("regex", ctfield.getCustomTableFieldRegex());
          map.put("regexText", ctfield.getCustomTableFieldRegexExample());          
          fields.add(map);

      }
      
      metaData.put("fields",fields);
  }

  private void populateMetaData(CustomTable table,ModelMap modelMap) { 
      Map<String, Object> metaData = new HashMap<String, Object>();    
      metaData.put("idProperty","id");
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
}
