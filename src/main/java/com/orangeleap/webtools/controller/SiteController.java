package com.orangeleap.webtools.controller;

import com.orangeleap.webtools.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class SiteController {

   @Autowired
   protected SiteService siteService = null;
	   
	  
   @RequestMapping("/site.htm")
   public void site(Model model) {
   }
}
