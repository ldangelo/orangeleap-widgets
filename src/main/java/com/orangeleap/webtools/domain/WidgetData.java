package com.orangeleap.webtools.domain;

import java.util.Map;
import java.util.HashMap;

public class WidgetData {
  Map<String,String> data;

  public WidgetData() {
    data = new HashMap<String,String>();
  }

  public Map<String,String> getData() {
    return data;
  }

  public void setData(Map<String,String> data) {
    this.data = data;
  }
}
