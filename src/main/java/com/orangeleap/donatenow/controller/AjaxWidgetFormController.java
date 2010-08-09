package com.orangeleap.donatenow.controller;

import org.apache.commons.beanutils.PropertyUtils;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.donatenow.domain.CustomEntity;
import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.service.OrangeLeapClientService;
import com.orangeleap.donatenow.service.WidgetService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AjaxWidgetFormController extends MultiActionController {
  @Autowired
  WidgetService widgetService;

  public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception {
    return getModelMapError("Unimplemented");
  }

  public ModelAndView read(HttpServletRequest request,HttpServletResponse response) throws Exception {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String widgettype = request.getParameter("widgettype");
    String customentitytype = request.getParameter("customentitytype");
	String userName = auth.getName();
	String password = (String) auth.getCredentials();

    List<Widget> ret = widgetService.getWidgets(userName,password,widgettype,customentitytype);

    return getModelMap(ret,widgettype,customentitytype);
  }

  public ModelAndView create(HttpServletRequest request,HttpServletResponse response) throws Exception {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String widgettype = request.getParameter("widgettype");
    String customentitytype = request.getParameter("customentitytype");
	String userName = auth.getName();
	String password = (String) auth.getCredentials();

    Widget ret = widgetService.createWidget(userName,password,widgettype,customentitytype);
    ret.setWidgetId(0L);
    ret.setWidgetHtml("Undefined");
    return getModelMap(ret,widgettype,customentitytype);    
  }

  private void populateWidget(Widget widget,HttpServletRequest request) {
    Map paramaterMap = request.getParameterMap();
    Set keySet = paramaterMap.keySet();
    Iterator it = keySet.iterator();

    while (it.hasNext()) {
      String key = (String) it.next();
      String value = "";

      // 
      // see if a widget has this property
      try {
        PropertyUtils.setSimpleProperty(widget,key,value);
      } catch (Exception e) {
        // no op...
      }
    }
  }

  public ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws Exception {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String widgettype = request.getParameter("widgettype");
    String customentitytype = request.getParameter("customentitytype");
	String userName = auth.getName();
	String password = (String) auth.getCredentials();
    Widget widget = widgetService.createWidget(userName,password,widgettype,customentitytype);

    populateWidget(widget,request);

    widget.setWidgetId(0L);
    widget.setWidgetGuid(UUID.randomUUID().toString());
    widget.setWidgetLoginSuccessURL(request.getParameter("widgetLoginSuccessURL"));
    widget.setWidgetLoginFailureURL(request.getParameter("widgetLoginFailureURL"));
    widget.setWidgetHtml("<html>Your Code here!</html>");
    widget.setWidgetCreateDate(new Date());
    widget.setWidgetErrorCount(0L);
    widget.setWidgetViewCount(0L);
    widgetService.saveWidget(widget);
    
    
    return getModelMap(widget,widgettype,customentitytype);
  }

  public ModelAndView save(HttpServletRequest request,HttpServletResponse response) throws Exception {
    return getModelMapError("Unimplemented");
  }

  private ModelAndView getModelMap(List<Widget> widgets,String widgettype,String customentitytype) {
    List <Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();   
    Map<String,Object> modelMap = new HashMap<String,Object>();
    Map<String,Object> metaData = new HashMap<String,Object>();

    metaData.put("idProperty","id");
    metaData.put("root","rows");
    metaData.put("totalProperty","totalRows");
    metaData.put("successProperty","success");

    List<Map<String,Object>> fields = new ArrayList<Map<String,Object>>();    
    List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();    
    Map<String,Object> map = new HashMap<String,Object>();


    map = new HashMap<String,Object>();
    map.put("name","widgetGuid");
    map.put("readonly",true);
    map.put("type","text");
    map.put("header","Widget GUID");
    fields.add(map);

    map = new HashMap<String,Object>();
    map.put("name","widgetLoginSuccessURL");
    map.put("readonly",false);
    map.put("type","text");
    map.put("header","Widget Success URL");
    fields.add(map);

    map = new HashMap<String,Object>();
    map.put("name","widgetLoginFailureURL");
    map.put("readonly",false);
    map.put("type","text");
    map.put("header","Login Failure  URL");
    fields.add(map);

    map = new HashMap<String,Object>();
    map.put("name","widgetHtml");
    map.put("readonly",true);
    map.put("type","comment");
    map.put("header","Widget HTML");
    fields.add(map);

    metaData.put("fields",fields);

    modelMap.put("metaData",metaData);
    modelMap.put("rows",widgets);
    modelMap.put("success",true);
    modelMap.put("totalRows",widgets.size());
    return new ModelAndView("jsonView",modelMap);
  }

  private ModelAndView getModelMap(Widget widget,String widgettype,String customentitytype) {
    List <Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();   
    Map<String,Object> modelMap = new HashMap<String,Object>();
    Map<String,Object> metaData = new HashMap<String,Object>();

    metaData.put("idProperty","widgetId");
    metaData.put("root","rows");
    metaData.put("totalProperty","totalRows");
    metaData.put("successProperty","success");

    List<Map<String,Object>> fields = new ArrayList<Map<String,Object>>();    
    List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();    
    Map<String,Object> map = new HashMap<String,Object>();

    map = new HashMap<String,Object>();
    map.put("name","widgetGuid");
    map.put("readonly",true);
    map.put("type","text");
    map.put("header","Widget GUID");
    fields.add(map);

    map = new HashMap<String,Object>();
    map.put("name","widgetLoginSuccessURL");
    map.put("readonly",false);
    map.put("type","text");
    map.put("header","Login Success URL");

    fields.add(map);
    map = new HashMap<String,Object>();
    map.put("name","widgetLoginFailureURL");
    map.put("readonly",false);
    map.put("type","text");
    map.put("header","Login Failure URL");
    fields.add(map);

    map = new HashMap<String,Object>();
    map.put("name","widgetHtml");
    map.put("readonly",true);
    map.put("type","comment");
    map.put("header","Widget HTML");
    fields.add(map);


    metaData.put("fields",fields);

    modelMap.put("metaData",metaData);
    modelMap.put("rows",widget);
    modelMap.put("success",true);
    modelMap.put("totalRows",1);
    return new ModelAndView("jsonView",modelMap);
  }

  private ModelAndView getModelMapError(String msg){

		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return new ModelAndView("jsonView",modelMap);
	} 
  
  
}