package com.orangeleap.donatenow.controller;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.orangeleap.donatenow.service.WidgetService;
import com.orangeleap.donatenow.service.OrangeLeapClientService;
import com.orangeleap.donatenow.domain.CustomEntity;
import com.orangeleap.donatenow.domain.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.Constituent;
import org.apache.commons.beanutils.PropertyUtils;

public class AjaxLoginWidgetController extends MultiActionController {
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

    return getModelMap(ret);
  }

  public ModelAndView create(HttpServletRequest request,HttpServletResponse response) throws Exception {
    return getModelMapError("Unimplemented");
  }

  public ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws Exception {
    return getModelMapError("Unimplemented");
  }

  public ModelAndView save(HttpServletRequest request,HttpServletResponse response) throws Exception {
    return getModelMapError("Unimplemented");
  }

  private ModelAndView getModelMap(List<Widget> widgets) {
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
    map.put("name","widgetId");
    map.put("header","Id");
    fields.add(map);

    map = new HashMap<String,Object>();
    map.put("name","widgetGuid");
    map.put("type","text");
    map.put("header","GUID");
    fields.add(map);
    metaData.put("fields",fields);

    map = new HashMap<String,Object>();
    map.put("name","widgetCreateDate");
    map.put("type","text");
    map.put("header","Create Date");
    fields.add(map);
    metaData.put("fields",fields);

    map = new HashMap<String,Object>();
    map.put("name","widgetViewCount");
    map.put("type","text");
    map.put("header","View Count");
    fields.add(map);
    metaData.put("fields",fields);

    map = new HashMap<String,Object>();
    map.put("name","widgetErrorCount");
    map.put("type","text");
    map.put("header","Error Count");
    fields.add(map);
    metaData.put("fields",fields);


    modelMap.put("metaData",metaData);
    modelMap.put("rows",widgets);
    modelMap.put("success",true);
    modelMap.put("totalRows",widgets.size());
    return new ModelAndView("jsonView",modelMap);
  }

  private ModelAndView getModelMapError(String msg){

		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return new ModelAndView("jsonView",modelMap);
	} 
  
  
}