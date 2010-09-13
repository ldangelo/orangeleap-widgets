/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.orangeleap.donatenow.controller;

import com.orangeleap.donatenow.dao.StyleDAO;
import com.orangeleap.donatenow.domain.Style;
import com.orangeleap.donatenow.service.StyleService;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author ldangelo
 */
public class AjaxStyleController extends MultiActionController {

    @Autowired
    protected StyleService styleService = null;

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        String strStyle = request.getParameter("Style");
        String strId = request.getParameter("Id");
        Style style = new Style();
        
        style.setUserName(userName);
        
        if (strStyle != null)
        	style.setStyle(strStyle);
        else
        	style.setStyle("");
        
        if (strId != null && !strId.equals("")) {
        	style.setId(Long.getLong(strId));
        	styleService.update(style);
        } else {
        	styleService.insert(style);
        }
        
        HashMap model = new HashMap();
        HashMap rows = new HashMap();
        
        rows.put("Id", style.getId());
        rows.put("Style",net.sf.json.util.JSONUtils.quote(style.getStyle()));
        model.put("Results", 1);
        model.put("rows", rows);
        
        return new ModelAndView("jsonView",model);    	
    }
    
    public ModelAndView read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Style style = styleService.selectByUserName(userName);
        
        if (style == null) style = new Style();
        HashMap model = new HashMap();
        HashMap rows = new HashMap();
        
        rows.put("Id", style.getId());
        rows.put("Style",net.sf.json.util.JSONUtils.quote(style.getStyle()));
        model.put("Results", 1);
        model.put("rows", rows);
        
        return new ModelAndView("jsonView",model);
    }
}
