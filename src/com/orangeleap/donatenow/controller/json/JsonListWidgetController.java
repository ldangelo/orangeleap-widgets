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

import com.orangeleap.donatenow.domain.DonateWidget;
import com.orangeleap.donatenow.service.DonateWidgetService;

@Controller
@RequestMapping("/listWidgets.json")
public class JsonListWidgetController {

   @Autowired
   protected DonateWidgetService donateWidgetService = null;
	   
	private void addWidgets(List widgets, List<Map<String, Object>> returnList) {
		Iterator<DonateWidget> it = widgets.iterator();
		while (it.hasNext()) {
			DonateWidget widget = (DonateWidget) it.next();
			
			// Add widgetid
			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("ID", "widgetid");
//			map.put("LABEL", "Widget Id");
			map.put("widgetid", widget.getId());
//			map.put("CLASS", "long");
			returnList.add(map);

			// add guid
//			map = new HashMap<String, Object>();
//			map.put("ID", "guid");
//			map.put("LABEL", "GUID");
//			map.put("VALUE", widget.getGuid());
			map.put("guid", widget.getGuid());
//			map.put("MAX_LEN", 255);
			returnList.add(map);
			
			// add projectcode
//			map = new HashMap<String, Object>();
//			map.put("ID", "projectcode");
//			map.put("LABEL", "Project Code");
			map.put("projectcode", widget.getProjectCode());
//			map.put("CLASS", "string");
//			map.put("MAX_LEN", 255);
			returnList.add(map);			
			
			// add motivationcode
//			map = new HashMap<String, Object>();
//			map.put("ID", "motivationcode");
//			map.put("LABEL", "Motivation Code");
			map.put("motivationcode", widget.getMotivationCode());
//			map.put("CLASS", "string");
//			map.put("MAX_LEN", 255);
			returnList.add(map);						
			
			// add giftcount
//			map = new HashMap<String, Object>();
//			map.put("ID", "giftcount");
//			map.put("LABEL", "Gift Count");
			map.put("giftcount", widget.getGiftCount());
//			map.put("CLASS", "long");
			returnList.add(map);									
			
			// add errocount
//			map = new HashMap<String, Object>();
//			map.put("ID", "errorcount");
//			map.put("LABEL", "Error Count");
			map.put("errorcount", widget.getErrorCount());
//			map.put("CLASS", "long");
			returnList.add(map);												
			
			map.put("viewcount", widget.getViewCount());
			returnList.add(map);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public void getWidgetList(ModelMap modelMap) {
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String userName = auth.getName();
		String password = (String) auth.getCredentials();
		List widgets = donateWidgetService.listWidgets(userName, password);
		
		addWidgets(widgets,returnList);
		
		modelMap.put("rows", returnList);
		modelMap.put("totalRows", returnList.size());
	}
}
