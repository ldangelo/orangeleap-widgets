package com.orangeleap.webtools.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.service.WidgetService;

@Controller

public class ListWidgetController {

	   @Autowired
	    protected WidgetService widgetService = null;
	   
	  
  @RequestMapping("/listwidgets.htm")
  public void widgets(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
    String userName = auth.getName();
    String password = (String) auth.getCredentials();
    List<Widget> widgets = widgetService.listWidgets(userName, password);
    model.addAttribute("widgets",widgets);
  }

  @RequestMapping("/listloginwidgets.htm")
  public void loginwidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/listdonationwidgets.htm")
  public void donationwidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/listdonorprofilewidgets.htm")
  public void donorprofilewidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/listgifthistorywidgets.htm")
  public void gifthistorywidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/listregistrationwidgets.htm")
  public void registrationwidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/listsponsorablewidgets.htm")
  public void sponsorablewidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/listsponsorshipwidgets.htm")
  public void sponsorshipwidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  
}
