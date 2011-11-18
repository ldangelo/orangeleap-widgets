package com.orangeleap.webtools.controller.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/listWidgets.json")
public class JsonListWidgetController {

   @Autowired
   protected WidgetService widgetService = null;
	   
	private void addWidgets(List<Widget> widgets, List<Map<String, Object>> returnList) {
		for (final Widget widget : widgets) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("widgetid", widget.getWidgetId());
			map.put("guid", widget.getWidgetGuid());
            map.put("type", widget.getWidgetType());
			map.put("widgetDescription", widget.getWidgetDescription());
			map.put("replaceTopContents", widget.getReplaceTopContents());
            map.put("entityname", widget.getCustomEntityName());
			map.put("errorcount", widget.getWidgetErrorCount());
			map.put("viewcount", widget.getWidgetViewCount());
			map.put("wid", widget.getWidgetViewCount());
			returnList.add(map);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public void getWidgetList(ModelMap modelMap) {
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String userName = auth.getName();
		String password = (String) auth.getCredentials();
		List<Widget> widgets = widgetService.listWidgets(userName, password);
		
		addWidgets(widgets,returnList);
		
		modelMap.put("rows", returnList);
		modelMap.put("totalRows", returnList.size());
	}
}
