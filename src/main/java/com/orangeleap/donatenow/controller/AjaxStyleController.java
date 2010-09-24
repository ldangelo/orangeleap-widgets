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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
        String strStyleName = request.getParameter("StyleName");
        Style style = new Style();

        style.setUserName(userName);
        style.setStyleName(strStyleName);
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

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Style style = new Style();
        HashMap model = new HashMap();
        HashMap rows = new HashMap();

        rows.put("Id", style.getId());
        rows.put("Style",net.sf.json.util.JSONUtils.quote(style.getStyle()));
        rows.put("StyleName", style.getStyleName());
        model.put("Results", 1);
        model.put("rows", rows);

        return new ModelAndView("jsonView",model);
    }

    public ModelAndView read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String strId = request.getParameter("Id");

        Style style = styleService.selectById(Long.valueOf(strId));

        if (style == null) style = new Style();
        HashMap model = new HashMap();
        HashMap rows = new HashMap();

        rows.put("Id", style.getId());
        rows.put("Style",net.sf.json.util.JSONUtils.quote(style.getStyle()));
        rows.put("StyleName", style.getStyleName());
        model.put("Results", 1);
        model.put("rows", rows);

        return new ModelAndView("jsonView",model);
    }

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Style style = new Style();
        style.setUserName(userName);
        List<Style> styles = null;

        if (userName != null && !userName.equals("")) {
        	styles = styleService.selectByUserName(userName);
        }

        HashMap model = new HashMap();
        List rows = new LinkedList();

        Iterator<Style> it = styles.iterator();
        while (it.hasNext()) {
        	HashMap row = new HashMap();
        	Style s = it.next();
        	row.put("Id",s.getId());
        	row.put("Style",net.sf.json.util.JSONUtils.quote(s.getStyle()));
        	row.put("StyleName", s.getStyleName());
        	rows.add(row);
        }

        HashMap metaData = new HashMap();
        HashMap meta = new HashMap();
        List fields = new LinkedList();
        meta.put("header", "Style Id");
        meta.put("name", "Id");
        fields.add(meta);

        meta = new HashMap();
        meta.put("header","Style");
        meta.put("name", "Style");
        fields.add(meta);

                meta = new HashMap();
        meta.put("header","Style Name");
        meta.put("name", "StyleName");
        fields.add(meta);

        metaData.put("idProperty", "id");
        metaData.put("totalProperty","results");
        metaData.put("root", "rows");
        metaData.put("fields", fields);

        model.put("results", styles.size());
        model.put("metaData", metaData);
        model.put("rows", rows);

        return new ModelAndView("jsonView",model);
     }
}
