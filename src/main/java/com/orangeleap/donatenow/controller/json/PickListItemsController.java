package com.orangeleap.donatenow.controller.json;
import com.orangeleap.client.GetPickListByNameRequest;
import com.orangeleap.client.GetPickListByNameResponse;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.WSClient;
import com.orangeleap.donatenow.dao.WidgetDAO;
import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.domain.WidgetExample;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/picklistItems.json")
public class PickListItemsController {
  @Autowired
  WidgetDAO widgetDAO = null;

  @RequestMapping(method=RequestMethod.POST)
  public void getPickListItems(@RequestParam(required=true) String guid, @RequestParam(required=true) String picklistname, ModelMap modelMap) {
    List <Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
    WidgetExample example = new WidgetExample();
    example.createCriteria().andWidgetGuidEqualTo(guid);
    
    List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
    
    if (widgets.size() > 0) {
      Widget widget = widgets.get(0);
      
      String wsusername = widget.getWidgetUsername();
      String wspassword = widget.getWidgetPassword();
      
      WSClient wsClient = null;
      OrangeLeap oleap = null;
      
      wsClient = new WSClient();
      oleap = wsClient.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),wsusername, wspassword);
      GetPickListByNameRequest request = new GetPickListByNameRequest();
      GetPickListByNameResponse response = null;
      
      request.setName(picklistname);
      response = oleap.getPickListByName(request);
      if (response != null) {
        populateMetaData(response.getPicklist().getPicklistItems(),returnList);
        modelMap.put("success",true);
        modelMap.put("rows", returnList);
        modelMap.put("totalRows", returnList.size());
      }
    }
  }
  
  private  void populateMetaData(List<PicklistItem> picklistitems,List<Map<String,Object>> returnList) {
    Iterator<PicklistItem> it = picklistitems.iterator();

    while (it.hasNext()) {
      PicklistItem item = it.next();
      Map<String, Object> map = new HashMap<String, Object>();    
      map.put("Name",item.getItemName());
      map.put("Description",item.getLongDescription());

      returnList.add(map);
    }
  }
}