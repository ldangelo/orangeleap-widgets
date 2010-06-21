package com.orangeleap.donatenow.service.impl;

import java.util.Date;
import com.orangeleap.donatenow.dao.WidgetDAO;
import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.domain.WidgetExample;
import com.orangeleap.donatenow.service.WidgetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.orangeleap.client.CustomTable;
import com.orangeleap.donatenow.domain.WidgetData;

@Service("widgetService")
public class WidgetServiceImpl implements WidgetService {
    private static final Log logger = LogFactory.getLog(WidgetServiceImpl.class);
  @Autowired
  WidgetDAO widgetDAO = null;

  public Widget create() {
    return new Widget();
  }
  public Widget selectWidgetById(Long id) {
    return widgetDAO.selectWidgetByPrimaryKey(id);
  }

  public CustomTable getCustomEntity(String name) {
    return null;
  }

  public WidgetData process(WidgetData data) {
    return data;
  }

  public List<Widget> listWidgets(String userName, String password) {
    WidgetExample example = new WidgetExample();

    example.createCriteria().andWidgetUsernameEqualTo(userName);
    example.createCriteria().andWidgetPasswordEqualTo(password);

    return widgetDAO.selectWidgetByExample(example);
  }

  public void updateViewCount(String guid, String refererrer)
  {
    WidgetExample example = new WidgetExample();
    example.createCriteria().andWidgetGuidEqualTo(guid);
    List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
    
    //
    // guid is a unique key so this will only return one widget
    Widget widget = widgets.get(0);

    widget.setWidgetViewCount(widget.getWidgetViewCount() + 1);
    widgetDAO.updateWidgetByPrimaryKey(widget);
  }

  public Widget saveOrUpdate(Widget widget) {
	if (widget.getWidgetCreateDate() == null)
	    widget.setWidgetCreateDate(new Date());
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	String userName = auth.getName();
	String password = (String) auth.getCredentials();
	String siteName = userName.substring(userName.indexOf('@') + 1);
	widget.setWidgetUsername(userName);
	widget.setWidgetPassword(password);

	
	Widget result = null;
	
	if (widget.getWidgetId() != null)
		widgetDAO.updateWidgetByPrimaryKey(widget);
	else
		widgetDAO.insertWidget(widget);
	
    return widget;
  }
}