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

public class WidgetFormController {

	   @Autowired
	    protected WidgetService widgetService = null;
	   
  @RequestMapping("/styleform.htm")
  public void styleform(Model model) {

  }

  @RequestMapping("/loginwidgetform.htm")
  public void loginwidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/donationwidgetform.htm")
  public void donationwidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/donorprofilewidgetform.htm")
  public void donorprofilewidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/gifthistorywidgetform.htm")
  public void gifthistorywidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/registrationwidgetform.htm")
  public void registrationwidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/sponsorablewidgetform.htm")
  public void sponsorablewidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  @RequestMapping("/sponsorshipwidgetform.htm")
  public void sponsorshipwidgets(@RequestParam(required=false) Long id, Model model) {
    
  }

  
}
