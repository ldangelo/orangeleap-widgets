package com.orangeleap.webtools.controller.json;

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


import com.orangeleap.webtools.domain.Placements;
import com.orangeleap.webtools.service.PlacementsService;

@Controller
@RequestMapping("/listPlacements.json")
public class JsonPlacementWidgetController {
	@Autowired
	PlacementsService placementsService;
	
	private void addPlacements(List<Placements> placements, List<Map<String, Object>> returnList) {
		Iterator<Placements> it = placements.iterator();
		
		while (it.hasNext()) {
			Placements placement = it.next();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", placement.getPlacementId());
			map.put("errorcount", placement.getErrorCount());
			map.put("url", placement.getPlacementUrl());
			map.put("widgetid", placement.getWidgetId());
			returnList.add(map);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void getPlacementList(@RequestParam(required=true) Long widgetid,ModelMap modelMap) {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		List<Placements> placements = placementsService.listPlacementsByWidgetId(widgetid);
		addPlacements(placements,returnList);
		modelMap.put("rows", returnList);
		modelMap.put("totalRows", returnList.size());
	}
}
