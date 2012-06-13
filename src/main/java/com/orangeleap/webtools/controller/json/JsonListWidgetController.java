package com.orangeleap.webtools.controller.json;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
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
			map.put("inactive", widget.isInactive());
			map.put("createdBy", widget.getWidgetUsername());

			String widgetName = "";
			if (widget.getWidgetType().equals("customentity") && widget.getCustomEntityName().equals("widget_authentication")) {
				widgetName = "Login";
			}
			else if (widget.getWidgetType().equals("customentity") && widget.getCustomEntityName().equals("online_donation")) {
				widgetName = "Donation";
			}
			else if (widget.getWidgetType().equals("pledges")) {
				widgetName = "Pledge";
			}
			else if (widget.getWidgetType().equals("customentity") && widget.getCustomEntityName().equals("online_recurringgift")) {
				widgetName = "Recurring Gift";
			}
			else if (widget.getWidgetType().equals("customentity") && widget.getCustomEntityName().equals("online_registration")) {
				widgetName = "Registration";
			}
			else if (widget.getWidgetType().equals("customentity") && widget.getCustomEntityName().equals("donor_profile")) {
				widgetName = "Donor Profile";
			}
			else if (widget.getWidgetType().equals("gifthistory")) {
				widgetName = "Gift History";
			}
			else if (widget.getWidgetType().equals("customentity") && widget.getCustomEntityName().equals("online_sponsorship")) {
				widgetName = "Sponsorship";
			}
			else if (widget.getWidgetType().equals("customentity") && widget.getCustomEntityName().equals("sponsorable")) {
				widgetName = "Sponsorable";
			}
			map.put("widgetname", widgetName);

			returnList.add(map);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public void getWidgetList(final HttpServletRequest request, final ModelMap modelMap) {
		
		final List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		final String filterByCreatedBy = request.getParameter("createdBy");
		boolean showOnlyForUser = false;
		if (filterByCreatedBy == null || "me".equalsIgnoreCase(filterByCreatedBy)) {
			showOnlyForUser = true;
		}
		final List<Widget> widgets = widgetService.listWidgets(showOnlyForUser);
		
		addWidgets(widgets,returnList);
		
		modelMap.put("rows", returnList);
		modelMap.put("totalRows", returnList.size());
	}
}
