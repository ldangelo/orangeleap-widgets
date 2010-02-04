package com.orangeleap.donatenow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orangeleap.donatenow.service.DonateWidgetService;

@Controller
@RequestMapping("/placements.htm")
public class PlacementController {
	@Autowired
    protected DonateWidgetService donateWidgetService = null;

 	@RequestMapping(method = RequestMethod.GET)
	void listPlacements(Model model) {
 		
	}
}
