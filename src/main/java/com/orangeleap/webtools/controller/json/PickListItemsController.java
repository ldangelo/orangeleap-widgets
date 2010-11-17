package com.orangeleap.webtools.controller.json;
import com.orangeleap.client.GetPickListByNameRequest;
import com.orangeleap.client.GetPickListByNameResponse;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.WSClient;
import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetExample;
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
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

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

    String wsusername = null;
    String wspassword = null;

    if (widgets.size() > 0 || guid.equals("")) {
      if (guid.equals("")) {
        //
        // get the authenticated user...
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        wsusername = auth.getName();
        wspassword = (String) auth.getCredentials();
      } else {

        Widget widget = widgets.get(0);

        wsusername = widget.getWidgetUsername();
        wspassword = widget.getWidgetPassword();
      }

      WSClient wsClient = null;
      OrangeLeap oleap = null;

      wsClient = new WSClient();
      oleap = wsClient.getOrangeLeap(System.getProperty("webtools.wsdllocation"),wsusername, wspassword);
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

      if (item.getLongDescription() != null && !item.getLongDescription().equals(""))
    	  map.put("Description",item.getLongDescription());
      else
    	  map.put("Description",item.getItemName());

      returnList.add(map);
    }
  }
}