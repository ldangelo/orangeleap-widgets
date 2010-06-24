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
import com.orangeleap.donatenow.dao.WidgetDAO;
import com.orangeleap.donatenow.domain.Widget;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.orangeleap.donatenow.service.WidgetService;

@Controller
@RequestMapping("/widget.htm")
public class WidgetController {
    private static final Log logger = LogFactory.getLog(WidgetController.class);
    
    @Autowired
  protected WidgetService widgetService = null;
    
  public Widget newRequest(@RequestParam(required=false) Long id,ModelMap map) {
    return (id != null ? widgetService.selectWidgetById(id) : widgetService.create());
  }
    
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
    	Widget widget = null;
    	if (id == null)
          widget = widgetService.create();
    	else
    		widget = widgetService.selectWidgetById(id);

		model.addAttribute(widget);
    }
    
    /**
     * <p>Saves a DonateNowWidget.</p>
     * 
     * <p>Expected HTTP POST and request '/donatewidget/form'.</p>
     */
    @RequestMapping(method = RequestMethod.POST)
    public void form(Widget widget, Model model) {
      widgetService.saveOrUpdate(widget);
	
      model.addAttribute("statusMessageKey","widget.form.msg.success");
    }
}
