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

import com.orangeleap.donatenow.dao.WidgetDAO;
import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.service.WidgetService;

@Controller
@RequestMapping("/listwidgets.htm")
public class ListWidgetController {

	   @Autowired
	    protected WidgetService widgetService = null;
	   
	   @RequestMapping(method = RequestMethod.GET)
       public void form(Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			String userName = auth.getName();
			String password = (String) auth.getCredentials();
		   List<Widget> widgets = widgetService.listWidgets(userName, password);
		   model.addAttribute("widgets",widgets);
	   }
}
