package com.orangeleap.webtools.controller.json;

import com.orangeleap.client.PicklistItem;
import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetExample;
import com.orangeleap.webtools.service.PicklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/picklistItems.json")
public class PickListItemsController {
    @Autowired
    WidgetDAO widgetDAO = null;

    @Autowired
    PicklistService picklistService;

    @RequestMapping(method = RequestMethod.POST)
    public void getPickListItems(@RequestParam(required = true) String guid, @RequestParam(required = true) String picklistname, ModelMap modelMap) {
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
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
            List<PicklistItem> picklistItems = picklistService.getPickListItems(wsusername, wspassword, picklistname,true);

            if (picklistItems != null) {
                populateMetaData(picklistItems, returnList);
                modelMap.put("success", true);
                modelMap.put("rows", returnList);
                modelMap.put("totalRows", returnList.size());
            }
        }
    }

    private void populateMetaData(List<PicklistItem> picklistitems, List<Map<String, Object>> returnList) {
        Iterator<PicklistItem> it = picklistitems.iterator();
        String desc;
        while (it.hasNext()) {
            PicklistItem item = it.next();
            Map<String, Object> map = new HashMap<String, Object>();

            if (item.isInactive() != true) {
                map.put("Name", item.getItemName());

                desc = item.getDefaultDisplayValue();
                map.put("Description", desc);

                returnList.add(map);
            }
        }
    }
}