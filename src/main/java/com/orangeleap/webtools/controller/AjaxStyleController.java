/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.orangeleap.webtools.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.orangeleap.webtools.domain.Style;
import com.orangeleap.webtools.service.StyleService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author ldangelo
 */
public class AjaxStyleController extends MultiActionController {

	@Autowired
	protected StyleService styleService = null;

	@SuppressWarnings("unchecked")
	public ModelAndView save(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String userName = auth.getName();
		final String userSiteName = resolveSiteName(userName);
		final String strStyle = request.getParameter("Style");
		final String strId = request.getParameter("Id");
		final String strStyleName = request.getParameter("StyleName");
		final boolean inactive = "on".equalsIgnoreCase(request.getParameter("Inactive"));
		final boolean deleted = "on".equalsIgnoreCase(request.getParameter("Deleted"));

		Style style;
		if (NumberUtils.isDigits(strId)) {
			style = styleService.selectById(Long.valueOf(strId));

			if (style != null && style.getSiteName() != null && ! style.getSiteName().equals(userSiteName))  {
				response.setHeader("errorMsg", userName + " is not authorized to view Style ID: " + style.getId());
				if (logger.isWarnEnabled()) {
					logger.warn("!! UNAUTHORIZED ACCESS !!! " + userName + " is not authorized to view Style ID: " + style.getId());
				}
				return null;
			}
		}
		else {
			style = new Style();
		}

		style.setUserName(userName);
		style.setStyleName(strStyleName);
		if (strStyle != null) {
			style.setStyle(strStyle);
		}
		else {
			style.setStyle("");
		}
		style.setInactive(inactive);
		style.setDeleted(deleted);

		if (NumberUtils.isDigits(strId)) {
			styleService.update(style);
		}
		else {
			styleService.insert(style);
		}

		final Map model = new HashMap();
		final Map<String, Object> rows = new HashMap<String, Object>();

		rows.put("Id", style.getId());
		rows.put("Style", net.sf.json.util.JSONUtils.quote(style.getStyle()));
		model.put("Results", 1);
		model.put("rows", rows);
		model.put("success", true);

		return new ModelAndView("jsonView", model);
	}

	@SuppressWarnings("unchecked")
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		Style style = new Style();
		final Map model = new HashMap();
		final Map<String, Object> rows = new HashMap<String, Object>();

		rows.put("Id", style.getId());
		rows.put("Style", net.sf.json.util.JSONUtils.quote(style.getStyle()));
		rows.put("StyleName", style.getStyleName());
		rows.put("Inactive", style.isInactive());
		rows.put("Deleted", style.isDeleted());
		model.put("Results", 1);
		model.put("rows", rows);

		return new ModelAndView("jsonView", model);
	}

	@SuppressWarnings("unchecked")
	public ModelAndView read(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		final String strId = request.getParameter("Id");
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String userSiteName = resolveSiteName(auth.getName());

		Style style = styleService.selectById(Long.valueOf(strId));

		if (style == null) {
			style = new Style();
		}
		else if (style.getSiteName() != null && ! style.getSiteName().equals(userSiteName))  {
			response.setHeader("errorMsg", auth.getName() + " is not authorized to view Style ID: " + style.getId());
			if (logger.isWarnEnabled()) {
				logger.warn("!! UNAUTHORIZED ACCESS !!! " + auth.getName() + " is not authorized to view Style ID: " + style.getId());
			}
			return null;
		}

		final Map model = new HashMap();
		final Map<String, Object> rows = new HashMap<String, Object>();

		rows.put("Id", style.getId());
		rows.put("Style", net.sf.json.util.JSONUtils.quote(style.getStyle()));
		rows.put("StyleName", style.getStyleName());
		rows.put("Inactive", style.isInactive() ? "on" : "off");
		rows.put("Deleted", style.isDeleted() ? "on" : "off");
		model.put("Results", 1);
		model.put("rows", rows);

		return new ModelAndView("jsonView", model);
	}

	@SuppressWarnings("unchecked")
	public ModelAndView list(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String userName = auth.getName();
		final Style style = new Style();
		style.setUserName(userName);
		List<Style> styles = null;

		if (userName != null && ! userName.equals("")) {
			final String filterByCreatedBy = request.getParameter("createdBy");
			if (filterByCreatedBy == null || "me".equalsIgnoreCase(filterByCreatedBy)) {
				styles = styleService.selectByUserName(userName);
			}
			else {
				final String siteName = resolveSiteName(userName);
				if (StringUtils.isNotBlank(siteName)) {
					styles = styleService.selectBySiteName(siteName);
				}
			}
		}

		final Map model = new HashMap();
		List rows = new LinkedList();

		Map<String, Object> row = new HashMap<String, Object>();
		row.put("Id", "0");
		row.put("Style", "");
		row.put("StyleName", "Default");
		row.put("Inactive", false);
		rows.add(row);

		if (styles != null) {
			for (final Style s : styles) {
				row = new HashMap<String, Object>();
				row.put("Id", s.getId());
				row.put("Style", net.sf.json.util.JSONUtils.quote(s.getStyle()));
				row.put("StyleName", s.getStyleName());
				row.put("Inactive", false);
				row.put("CreatedBy", s.getUserName());
				rows.add(row);
			}
		}

		Map metaData = new HashMap();
		Map meta = new HashMap();
		List fields = new LinkedList();
		meta.put("header", "Style Id");
		meta.put("name", "Id");
		fields.add(meta);

		meta = new HashMap();
		meta.put("header", "Style Name");
		meta.put("name", "StyleName");
		fields.add(meta);

		meta = new HashMap();
		meta.put("header", "Style");
		meta.put("name", "Style");
		fields.add(meta);

		meta = new HashMap();
		meta.put("header", "Created By");
		meta.put("name", "CreatedBy");
		fields.add(meta);

		meta = new HashMap();
		meta.put("header", "Inactive");
		meta.put("name", "Inactive");
		fields.add(meta);

		metaData.put("idProperty", "id");
		metaData.put("totalProperty", "results");
		metaData.put("root", "rows");
		metaData.put("fields", fields);

		model.put("results", styles == null ? 0 : styles.size());
		model.put("metaData", metaData);
		model.put("rows", rows);

		return new ModelAndView("jsonView", model);
	}

	private String resolveSiteName(final String userName) {
		return userName == null ? null : userName.substring(userName.indexOf('@') + 1);
	}
}
