package com.orangeleap.donatenow.controller.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.service.WidgetService;

@Controller
@RequestMapping("/listWidgets.json")
public class JsonListWidgetController {

   @Autowired
   protected WidgetService widgetService = null;
	   
	private void addWidgets(List widgets, List<Map<String, Object>> returnList) {
		Iterator<Widget> it = widgets.iterator();
		while (it.hasNext()) {
			Widget widget = (Widget) it.next();
			
			// Add widgetid
			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("ID", "widgetid");
//			map.put("LABEL", "Widget Id");
			map.put("widgetid", widget.getWidgetId());
//			map.put("CLASS", "long");
			returnList.add(map);

			// add guid
//			map = new HashMap<String, Object>();
//			map.put("ID", "guid");
//			map.put("LABEL", "GUID");
//			map.put("VALUE", widget.getGuid());
			map.put("guid", widget.getWidgetGuid());
//			map.put("MAX_LEN", 255);
			returnList.add(map);

            map.put("type",widget.getWidgetType());
            returnList.add(map);
			
            map.put("entityname", widget.getCustomEntityName());
			// add errocount
//			map = new HashMap<String, Object>();
//			map.put("ID", "errorcount");
//			map.put("LABEL", "Error Count");
			map.put("errorcount", widget.getWidgetErrorCount());
//			map.put("CLASS", "long");
			returnList.add(map);												
			
			map.put("viewcount", widget.getWidgetViewCount());
			returnList.add(map);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public void getWidgetList(ModelMap modelMap) {
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String userName = auth.getName();
		String password = (String) auth.getCredentials();
		List widgets = widgetService.listWidgets(userName, password);
		
		addWidgets(widgets,returnList);
		
		modelMap.put("rows", returnList);
		modelMap.put("totalRows", returnList.size());
	}
}
