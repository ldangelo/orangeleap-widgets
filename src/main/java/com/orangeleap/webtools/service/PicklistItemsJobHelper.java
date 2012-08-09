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

	  public void updateCache() throws JobExecutionException {
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
