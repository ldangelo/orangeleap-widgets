package com.orangeleap.donatenow.controller;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.orangeleap.donatenow.service.OrangeLeapClientService;
import com.orangeleap.donatenow.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.Constituent;
import org.apache.commons.beanutils.PropertyUtils;

@Controller

public class ListLoginWidgetController extends MultiActionController {
  @Autowired
  WidgetService widgetService;

}