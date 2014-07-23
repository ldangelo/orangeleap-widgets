package com.orangeleap.webtools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.orangeleap.webtools.service.JavascriptService;

@Controller
public class JavascriptController {

    @Autowired
    protected JavascriptService javascriptService;
	  
    @RequestMapping("/javascriptlist.htm")
    public void javascriptList(Model model) {
   
    }

	@RequestMapping("/javascriptform.htm")
	public void javascriptForm(Model model) {

	}
}