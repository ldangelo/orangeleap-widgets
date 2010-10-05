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
public class WidgetFormController {

	@Autowired
	protected WidgetService widgetService = null;

	@RequestMapping("/createwidget.htm")
	public void createwidget(Model model) {

	}

	@RequestMapping("/styleform.htm")
	public void styleform(Model model) {

	}

	@RequestMapping("/stylelist.htm")
	public void stylelist(Model model) {

	}

	@RequestMapping("/donatewidget.htm")
	public void donatewidget(Model model) {

	}

	@RequestMapping("/widgetform.htm")
	public void widgets(@RequestParam(required = false) Long id, Model model) {

	}

	@RequestMapping("/loginwidgetform.htm")
	public void loginwidgets(@RequestParam(required = false) Long id,
			Model model) {

	}

	@RequestMapping("/donationwidgetform.htm")
	public void donationwidgets(@RequestParam(required = false) Long id,
			Model model) {

	}

	@RequestMapping("/donorprofilewidgetform.htm")
	public void donorprofilewidgets(@RequestParam(required = false) Long id,
			Model model) {

	}

	@RequestMapping("/gifthistorywidgetform.htm")
	public void gifthistorywidgets(@RequestParam(required = false) Long id,
			Model model) {

	}

	@RequestMapping("/registrationwidgetform.htm")
	public void registrationwidgets(@RequestParam(required = false) Long id,
			Model model) {

	}

	@RequestMapping("/sponsorablewidgetform.htm")
	public void sponsorablewidgets(@RequestParam(required = false) Long id,
			Model model) {

	}

	@RequestMapping("/sponsorshipwidgetform.htm")
	public void sponsorshipwidgets(@RequestParam(required = false) Long id,
			Model model) {

	}

}
