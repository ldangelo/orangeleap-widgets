package com.orangeleap.webtools.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.orangeleap.webtools.domain.Javascript;
import com.orangeleap.webtools.service.JavascriptService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AjaxJavascriptController extends MultiActionController {

	@Autowired
	protected JavascriptService javascriptService = null;

	public ModelAndView save(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String userName = auth.getName();
		final String userSiteName = resolveSiteName(userName);
		final String strJavascript = request.getParameter("Javascript");
		final String strId = request.getParameter("Id");
		final String strJavascriptName = request.getParameter("JavascriptName");
		final boolean inactive = "on".equalsIgnoreCase(request.getParameter("Inactive"));
		final boolean deleted = "on".equalsIgnoreCase(request.getParameter("Deleted"));

		Javascript javascript;
		if (NumberUtils.isDigits(strId)) {
			javascript = javascriptService.selectById(Long.valueOf(strId));

			if (javascript != null && javascript.getSiteName() != null && ! javascript.getSiteName().equals(userSiteName))  {
				response.setHeader("errorMsg", userName + " is not authorized to view Javascript ID: " + javascript.getJavascriptId());
				if (logger.isWarnEnabled()) {
					logger.warn("!! UNAUTHORIZED ACCESS !!! " + userName + " is not authorized to view Javascript ID: " + javascript.getJavascriptId());
				}
				return null;
			}
		}
		else {
			javascript = new Javascript();
		}

		javascript.setUserName(userName);
		javascript.setJavascriptName(strJavascriptName);
		if (strJavascript != null) {
			javascript.setJavascript(strJavascript);
		}
		else {
			javascript.setJavascript("");
		}
		javascript.setInactive(inactive);
		javascript.setDeleted(deleted);

		if (NumberUtils.isDigits(strId)) {
			javascriptService.update(javascript);
		}
		else {
			javascriptService.insert(javascript);
		}

		final Map<String, Object> model = new HashMap<String, Object>();
		final Map<String, Object> rows = new HashMap<String, Object>();

		rows.put("Id", javascript.getJavascriptId());
		rows.put("Javascript", net.sf.json.util.JSONUtils.quote(javascript.getJavascript()));
		model.put("Results", 1);
		model.put("rows", rows);
		model.put("success", true);

		return new ModelAndView("jsonView", model);
	}

	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Javascript javascript = new Javascript();
		final Map<String, Object> model = new HashMap<String, Object>();
		final Map<String, Object> rows = new HashMap<String, Object>();

		rows.put("Id", javascript.getJavascriptId());
		rows.put("Javascript", net.sf.json.util.JSONUtils.quote(javascript.getJavascript()));
		rows.put("JavascriptName", javascript.getJavascriptName());
		rows.put("Inactive", javascript.isInactive());
		rows.put("Deleted", javascript.isDeleted());
		model.put("Results", 1);
		model.put("rows", rows);

		return new ModelAndView("jsonView", model);
	}

	public ModelAndView read(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		final String strId = request.getParameter("Id");
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String userSiteName = resolveSiteName(auth.getName());

		Javascript javascript = javascriptService.selectById(Long.valueOf(strId));

		if (javascript == null) {
			javascript = new Javascript();
		}
		else if (javascript.getSiteName() != null && ! javascript.getSiteName().equals(userSiteName))  {
			response.setHeader("errorMsg", auth.getName() + " is not authorized to view Javascript ID: " + javascript.getJavascriptId());
			if (logger.isWarnEnabled()) {
				logger.warn("!! UNAUTHORIZED ACCESS !!! " + auth.getName() + " is not authorized to view Javascript ID: " + javascript.getJavascriptId());
			}
			return null;
		}

		final Map<String, Object> model = new HashMap<String, Object>();
		final Map<String, Object> rows = new HashMap<String, Object>();

		rows.put("Id", javascript.getJavascriptId());
		rows.put("Javascript", net.sf.json.util.JSONUtils.quote(javascript.getJavascript()));
		rows.put("JavascriptName", javascript.getJavascriptName());
		rows.put("Inactive", javascript.isInactive() ? "on" : "off");
		rows.put("Deleted", javascript.isDeleted() ? "on" : "off");
		model.put("Results", 1);
		model.put("rows", rows);

		return new ModelAndView("jsonView", model);
	}

	public ModelAndView list(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String userName = auth.getName();
		final Javascript javascript = new Javascript();
		javascript.setUserName(userName);
		List<Javascript> javascripts = null;

		if (userName != null && ! userName.equals("")) {
			final String filterByCreatedBy = request.getParameter("createdBy");
			if (filterByCreatedBy == null || "me".equalsIgnoreCase(filterByCreatedBy)) {
				javascripts = javascriptService.selectByUserName(userName);
			}
			else {
				final String siteName = resolveSiteName(userName);
				if (StringUtils.isNotBlank(siteName)) {
					javascripts = javascriptService.selectBySiteName(siteName);
				}
			}
		}

		final Map<String, Object> model = new HashMap<String, Object>();
		List<Map<String, Object>> rows = new LinkedList<Map<String, Object>>();

		Map<String, Object> row = new HashMap<String, Object>();
		row.put("Id", "0");
		row.put("Javascript", "");
		row.put("JavascriptName", "Default");
		row.put("Inactive", false);
		rows.add(row);

		if (javascripts != null) {
			for (final Javascript s : javascripts) {
				row = new HashMap<String, Object>();
				row.put("Id", s.getJavascriptId());
				row.put("Javascript", net.sf.json.util.JSONUtils.quote(s.getJavascript()));
				row.put("JavascriptName", s.getJavascriptName());
				row.put("Inactive", s.isInactive());
				row.put("CreatedBy", s.getUserName());
				rows.add(row);
			}
		}

		Map<String, Object> metaData = new HashMap<String, Object>();
		Map<String, Object> meta = new HashMap<String, Object>();
		List<Map<String, Object>> fields = new LinkedList<Map<String, Object>>();
		meta.put("header", "Javascript Id");
		meta.put("name", "Id");
		fields.add(meta);

		meta = new HashMap<String, Object>();
		meta.put("header", "Javascript Name");
		meta.put("name", "JavascriptName");
		fields.add(meta);

		meta = new HashMap<String, Object>();
		meta.put("header", "Javascript");
		meta.put("name", "Javascript");
		fields.add(meta);

		meta = new HashMap<String, Object>();
		meta.put("header", "Last Modified By");
		meta.put("name", "CreatedBy");
		fields.add(meta);

		meta = new HashMap<String, Object>();
		meta.put("header", "Inactive");
		meta.put("name", "Inactive");
		fields.add(meta);

		metaData.put("idProperty", "id");
		metaData.put("totalProperty", "results");
		metaData.put("root", "rows");
		metaData.put("fields", fields);

		model.put("results", javascripts == null ? 0 : javascripts.size());
		model.put("metaData", metaData);
		model.put("rows", rows);

		return new ModelAndView("jsonView", model);
	}

	private String resolveSiteName(final String userName) {
		return userName == null ? null : userName.substring(userName.indexOf('@') + 1);
	}
}
