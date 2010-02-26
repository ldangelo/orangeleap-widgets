package com.orangeleap.donatenow.controller.json;

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

import com.orangeleap.donatenow.dao.PlacementDao;
import com.orangeleap.donatenow.domain.Placement;

@Controller
@RequestMapping("/listPlacements.json")
public class JsonPlacementWidgetController {
	@Autowired
	PlacementDao placementDao;
	
	private void addPlacements(List<Placement> placements, List<Map<String, Object>> returnList) {
		Iterator<Placement> it = placements.iterator();
		
		while (it.hasNext()) {
			Placement placement = it.next();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", placement.getId());
			map.put("errorcount", placement.getErrorCount());
			map.put("giftcount", placement.getGiftCount());
			map.put("viewcount", placement.getViewCount());
			map.put("url", placement.getPlacementURL());
			map.put("widgetid", placement.getWidgetid());
			returnList.add(map);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void getPlacementList(@RequestParam(required=true) Long widgetid,ModelMap modelMap) {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		List<Placement> placements = placementDao.listPlacementsByWidgetId(widgetid);
		addPlacements(placements,returnList);
		modelMap.put("rows", returnList);
		modelMap.put("totalRows", returnList.size());
	}
}
