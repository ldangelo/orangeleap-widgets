package com.orangeleap.donatenow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.orangeleap.donatenow.dao.DonateWidgetDao;
import com.orangeleap.donatenow.domain.DonateWidget;
import com.orangeleap.donatenow.service.DonateWidgetService;

@Controller
@RequestMapping("/listwidgets.htm")
public class ListWidgetController {

	   @Autowired
	    protected DonateWidgetService donateWidgetService = null;
	   
	   @RequestMapping(method = RequestMethod.GET)
       public void form(Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			String userName = auth.getName();
			String password = (String) auth.getCredentials();
		   List<DonateWidget> widgets = donateWidgetService.listWidgets(userName, password);
		   model.addAttribute("widgets",widgets);
	   }
}
