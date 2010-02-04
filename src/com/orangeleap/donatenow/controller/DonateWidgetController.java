package com.orangeleap.donatenow.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.orangeleap.client.GetPickListByNameRequest;
import com.orangeleap.client.GetPickListByNameResponse;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.WSClient;
import com.orangeleap.donatenow.dao.DonateWidgetDao;
import com.orangeleap.donatenow.domain.DonateWidget;

@Controller
@RequestMapping("/donatewidget.htm")
public class DonateWidgetController {
    
    @Autowired
    protected DonateWidgetDao donateWidgetDao = null;
    
    /*@ModelAttribute("donateWidget")
    public DonateWidget newRequest(@RequestParam(required=false) Integer id,ModelMap map) {
    	return (id != null ? donateWidgetDao.findWidgetById(id) : new DonateWidget());
    }*/
    
    /*
     * <p>Widget form request.</p>
     * 
     * <p>Expected HTTP Get and request '/donatewidget/form'.</p>

    @RequestMapping(method = RequestMethod.GET)
	public void form(Model model) {
    	model.addAttribute(new DonateWidget());
    }
     */
    
    @RequestMapping(method = RequestMethod.GET)
    public void form(@RequestParam(required=false) Long id,Model model) {
    	DonateWidget widget = null;
    	if (id == null)
    		widget = new DonateWidget();
    	else
    		widget = donateWidgetDao.findWidgetById(id);
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	String userName = auth.getName();
    	String password = (String) auth.getCredentials();   	
    	WSClient wsClient = new WSClient();
		OrangeLeap oleap = wsClient.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),userName, password);
		GetPickListByNameRequest request = new GetPickListByNameRequest();
		GetPickListByNameResponse response = null;
		
		request.setName("projectCode");
		response = oleap.getPickListByName(request);
		widget.setProjectCodeList(response.getPicklist().getPicklistItems());
		
		request.setName("motivationCode");
		response = oleap.getPickListByName(request);
		widget.setMotivationCodeList(response.getPicklist().getPicklistItems());		
		model.addAttribute(widget);
    }
    
    /**
     * <p>Saves a DonateNowWidget.</p>
     * 
     * <p>Expected HTTP POST and request '/donatewidget/form'.</p>
     */
    @RequestMapping(method = RequestMethod.POST)
    public void form(DonateWidget widget, Model model) {
	if (widget.getCreated() == null)
	    widget.setCreated(new Date());
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	String userName = auth.getName();
	String password = (String) auth.getCredentials();
	widget.setUserName(userName);
	widget.setPassWord(password);

	DonateWidget result = null;
	
	if (widget.getId() != null)
		donateWidgetDao.update(widget);
	else
		donateWidgetDao.insert(widget);
	
	// set id from create
	if (widget.getId() == null) widget.setId(result.getId());
	
	model.addAttribute("statusMessageKey","widget.form.msg.success");
    }
}
