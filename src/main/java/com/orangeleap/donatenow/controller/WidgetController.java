package com.orangeleap.donatenow.controller;

import com.orangeleap.client.Constituent;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.donatenow.domain.CustomEntity;
import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.domain.Style;
import com.orangeleap.donatenow.service.OrangeLeapClientService;
import com.orangeleap.donatenow.service.StyleService;
import com.orangeleap.donatenow.service.WidgetService;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class WidgetController extends MultiActionController {
  @Autowired
  WidgetService widgetService;

  @Autowired
  OrangeLeapClientService orangeLeapClientService;

  @Autowired
  StyleService styleService;

  public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String guid = request.getParameter("guid");
    String constituentid = request.getParameter("constituentId");
    Style style = null;
    String appLocation = System.getProperty("donatenow.applocation");

    if (constituentid == null || constituentid.equals("undefined") || constituentid.equals("")) {
      constituentid = "-1";
    }

    if (guid != null && !guid.equals("undefined")) {
      Widget w = widgetService.selectWidgetByGuid(guid);

      String customentitytype = w.getCustomEntityName();

      if (w.getStyleId() != null) 
        style = styleService.selectById(w.getStyleId());

      w.setWidgetHtml(w.getWidgetHtml().replaceAll("@APPLOCATION@",appLocation).replaceAll("@GUID@",w.getWidgetGuid()).replaceAll("@SUCCESSURL@",w.getWidgetLoginSuccessURL()).replaceAll("@FAILUREURL@",w.getWidgetLoginFailureURL()).replaceAll("@AUTHENTICATE@",w.getWidgetAuthenticationRequired().toString()).replaceAll("@LOGINURL@",w.getWidgetAuthenticationURL()).replaceAll("@PROJECTCODE@",w.getProjectCode()).replaceAll("@STYLE@",(style != null) ? style.getStyle() : ""));


      OutputStream out = response.getOutputStream();
      out.write(w.getWidgetHtml().getBytes());
    }

    return null;
  }

};
