package com.orangeleap.webtools.service;

import java.util.Iterator;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetExample;

public class PicklistItemsJob extends QuartzJobBean {
  @Autowired
  protected WidgetDAO widgetDAO;

  @Autowired
  protected PicklistService picklistService;
  
  protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
	  WidgetExample example = new WidgetExample();
	  
	  example.setOrderByClause("WIDGET_USERNAME ASC");
	  List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
	  Iterator<Widget> it = widgets.iterator();
	  
	  String lastUserID = "";
	  while(it.hasNext()) {
		  Widget w = it.next();
	
		  if (!w.getWidgetUsername().equals(lastUserID)) {
			  picklistService.getPickListItems(w.getWidgetUsername(), w.getWidgetPassword(), "projectCode",false);
			  picklistService.getPickListItems(w.getWidgetUsername(), w.getWidgetPassword(), "motivationCode",false);
			  lastUserID = w.getWidgetUsername();
		  }
		  
	  }
  }
}