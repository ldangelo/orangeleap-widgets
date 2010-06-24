package com.orangeleap.donatenow.service;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.domain.WidgetData;
import com.orangeleap.client.CustomTable;

public interface WidgetService {

  public Widget create();
  public Widget selectWidgetById(Long id);
  public List<Widget> listWidgets(String userName, String password);
  public void updateViewCount(String guid, String refererrer);
  public Widget saveOrUpdate(Widget widget);
  public CustomTable getCustomEntity(String name);
  public WidgetData process(WidgetData data);
}
