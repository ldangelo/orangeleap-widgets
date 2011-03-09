package com.orangeleap.webtools.service;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetData;
import com.orangeleap.webtools.domain.CustomEntity;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.CustomTableRow;
import javax.servlet.http.HttpServletRequest;

public interface WidgetService {
  public Widget saveWidget(Widget widget);
  public List<Widget> getLoginWidgets(String username,String password);
  public Widget createWidget(String username,String password,String widgettype,String customentitytype);
  public List<Widget> getWidgets(String username,String password,String widgettype,String customentitytype);
  public Widget getWidget(String guid);
  public List<CustomEntity> getCustomEntity(String guid);
  public CustomTableRow CreateCustomTableRow(String guid, HttpServletRequest request) throws Exception;
  public Widget create();
  public Widget selectWidgetById(Long id);
  public Widget selectWidgetByGuid(String guid);
  public List<Widget> listWidgets(String userName, String password);
  public void updateViewCount(String guid, String refererrer);
  public Widget saveOrUpdate(Widget widget);
  public CustomTable getCustomTable(String name);
  public WidgetData process(WidgetData data);
}
