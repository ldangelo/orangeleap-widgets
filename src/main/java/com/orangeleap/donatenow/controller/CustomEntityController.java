package com.orangeleap.donatenow.controller;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.orangeleap.donatenow.service.WidgetService;
import com.orangeleap.donatenow.service.OrangeLeapClientService;
import com.orangeleap.donatenow.domain.CustomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.Constituent;
import org.apache.commons.beanutils.PropertyUtils;

public class CustomEntityController extends MultiActionController {

  @Autowired
  WidgetService widgetService;

  @Autowired
  OrangeLeapClientService orangeLeapClientService;

  public ModelAndView view(HttpServletRequest request,HttpServletResponse response) throws Exception {
    String guid = request.getParameter("guid");
    String constituentid = request.getParameter("constituentid");

    if (constituentid == null || constituentid.equals("undefined") || constituentid.equals("")) {
      constituentid = "-1";
    }

    if (guid != null && !guid.equals("undefined")) {
      List<CustomEntity> ceList = widgetService.getCustomEntity(guid);
      return getModelMap(ceList,guid, new Long(constituentid));
    }

    return getModelMapError("Error trying to retrieve customEntity");
  }

	/**
	 * Generates modelMap to return in the modelAndView
	 * @param contacts
	 * @return
	 */
	private ModelAndView getModelMap(CustomTableRow row){
		
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", 1);
		modelMap.put("data", row);
		modelMap.put("success", true);
		
		return new ModelAndView("jsonView", modelMap);
	}

  public ModelAndView create(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    String guid = request.getParameter("guid");
    String constituentid = request.getParameter("constituentid");

    if (constituentid != null && !constituentid.equals("undefined") && !constituentid.equals("")) {
      orangeLeapClientService.removeFromCache(new Long(constituentid));
    }

    if (guid != null && !guid.equals("undefined")) {
      CustomTableRow row = widgetService.CreateCustomTableRow(guid,request);
      if (row != null) {
        return getModelMap(row);
      }

    }
    return getModelMapError("Error trying to create customEntity.");
  }

  public ModelAndView update(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return getModelMapError("Error trying to update customEntity");
  }

  public ModelAndView delete(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return getModelMapError("Error trying to delete customEntity");
  }

  private ModelAndView getModelMap(List<CustomEntity> ceList,String guid,Long constituentid) {
      List <Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();   
      Map<String,Object> modelMap = new HashMap<String,Object>();
      Map<String,Object> metaData = new HashMap<String,Object>();

    metaData.put("idProperty","id");
    metaData.put("root","rows");
    metaData.put("totalProperty","totalRows");
    metaData.put("successProperty","success");
    metaData.put("fields",ceList);

    modelMap.put("metaData",metaData);
    modelMap.put("success",true);
    
    Constituent constituent = null;

    if (constituentid != -1) {
      //
      // get the constituent associated with this custom entity
      constituent = orangeLeapClientService.getConstituentById(guid,constituentid);
    }

    //
    // add a row into the modelMap
    Iterator<CustomEntity> it = ceList.iterator();
    Map<String,Object> row = new HashMap<String,Object>();
    row.put("id",0);
    while (it.hasNext()) {
      CustomEntity ce = it.next();

      //
      // skip the id as we already put it in...
      if (ce.getName().equals("id")) continue;

        try {
          String ceName = ce.getName();
          
          if (ce.getExpression().startsWith("constituent")) {
            //
            // split the string on instances of '.'
            String args[] = ce.getExpression().split("\\.",-1);
            if (args.length == 2) {
              row.put(ce.getName(),PropertyUtils.getSimpleProperty(constituent,args[1]));
            } else if (args.length == 3) {
              Object obj = PropertyUtils.getSimpleProperty(constituent,args[1]);
              row.put(ce.getName(),PropertyUtils.getSimpleProperty(obj,args[2]));
            }
          }
        } catch (Exception e ) {
            row.put(ce.getName(),"");
        }
    }
    returnList.add(row);
    modelMap.put("rows",returnList);
    modelMap.put("totalRows",returnList.size()) ;

    return new ModelAndView("jsonView",modelMap);
  }

	/**
	 * Generates modelMap to return in the modelAndView in case
	 * of exception
	 * @param msg message
	 * @return
	 */
	private ModelAndView getModelMapError(String msg){

		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return new ModelAndView("jsonView",modelMap);
	} 
}