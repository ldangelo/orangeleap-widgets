package com.orangeleap.webtools.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.service.OrangeLeapClientService;
import com.orangeleap.webtools.service.PicklistService;
import com.orangeleap.webtools.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JsonClearCacheController {

	@Autowired
	protected WidgetService widgetService = null;

	@Autowired
	OrangeLeapClientService orangeLeapClientService;

	@Autowired
	PicklistService picklistService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/clearCache.json")
	public ModelMap clearCache() {
		final ModelMap modelMap = new ModelMap();
		
		final List<Widget> widgets = widgetService.listWidgets(false);

		for (Widget widget : widgets) {
			orangeLeapClientService.clearCache(widget.getWidgetGuid());
			picklistService.clearCache(widget.getWidgetUsername());
			widgetService.clearCache(widget.getWidgetGuid());
		}
		
		modelMap.put("clearCacheSuccess", Boolean.TRUE.toString());
		return modelMap;
	}
}
