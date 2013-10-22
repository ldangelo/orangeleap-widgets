package com.orangeleap.webtools.service;

import java.util.Iterator;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetExample;

public class PicklistItemsJobHelper {
	@Autowired
	  protected WidgetDAO widgetDAO;

	  @Autowired
	  protected PicklistService picklistService;

	  @Autowired
	  SiteService siteService;
	  
	  public void updateCache() throws JobExecutionException {
		  WidgetExample example = new WidgetExample();
		  
		  example.setOrderByClause("WIDGET_USERNAME ASC");
		  List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
		  Iterator<Widget> it = widgets.iterator();
		  
		  String lastUserID = "";
		  while(it.hasNext()) {
			  Widget w = it.next();

			  if (!w.getWidgetUsername().equals(lastUserID)) {
    			  com.orangeleap.webtools.domain.Site site = siteService.getSite(w.getSiteName());
				  picklistService.getPickListItems(site.getOrangeLeapUserId(), site.getOrangeLeapPassword(), "projectCode",false);
				  picklistService.getPickListItems(site.getOrangeLeapUserId(), site.getOrangeLeapPassword(), "motivationCode",false);
				  lastUserID = w.getWidgetUsername();
			  }
			  
		  }
	  }
}
