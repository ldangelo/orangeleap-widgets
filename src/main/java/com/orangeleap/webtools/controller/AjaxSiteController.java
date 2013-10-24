package com.orangeleap.webtools.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.orangeleap.webtools.domain.Site;
import com.orangeleap.webtools.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AjaxSiteController extends MultiActionController {

	private final static String MASK = "*****";
	
	@Autowired
	protected SiteService siteService = null;

	public ModelAndView save(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String userName = auth.getName();
		final String userSiteName = resolveSiteName(userName);
		final String orangeLeapUserId = request.getParameter("orangeLeapUserId");
		final String orangeLeapPassword = request.getParameter("orangeLeapPassword");
		
		Site site;
		if (userSiteName != null && userSiteName.length() > 0) {
			site = siteService.getSite(userSiteName);

			if (site != null && site.getSiteName() != null && ! site.getSiteName().equals(userSiteName))  {
				response.setHeader("errorMsg", userName + " is not authorized to view Site ID: " + site.getSiteName());
				if (logger.isWarnEnabled()) {
					logger.warn("!! UNAUTHORIZED ACCESS !!! " + userName + " is not authorized to view Site : " + site.getSiteName());
				}
				return null;
			} else {
				site = new Site();
			}
		}
		else {
			site = new Site();
		}

		site.setSiteName(userSiteName);
		site.setOrangeLeapUserId(orangeLeapUserId);
		if (!MASK.equals(orangeLeapPassword)) {
			site.setOrangeLeapPassword(orangeLeapPassword);
		}

		siteService.saveSite(site);

		final Map<String, Object> model = new HashMap<String, Object>();
		final Map<String, Object> rows = new HashMap<String, Object>();

		rows.put("siteName", site.getSiteName());
		rows.put("orangeLeapUserId", site.getOrangeLeapUserId());
		rows.put("orangeLeapPassword", MASK);
		model.put("Results", 1);
		model.put("rows", rows);
		model.put("success", true);

		return new ModelAndView("jsonView", model);
	}

	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		Site site = new Site();
		final Map<String, Object> model = new HashMap<String, Object>();
		final Map<String, Object> rows = new HashMap<String, Object>();

		rows.put("siteName", resolveSiteName(userName));
		rows.put("orangeLeapUserId", site.getOrangeLeapUserId());
		rows.put("orangeLeapPassword", MASK);
		model.put("Results", 1);
		model.put("rows", rows);

		return new ModelAndView("jsonView", model);
	}

	public ModelAndView read(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String userSiteName = resolveSiteName(auth.getName());

		Site site = siteService.getSite(userSiteName);

		if (site == null) {
			site = new Site();
		} else if (site.getSiteName() != null && ! site.getSiteName().equals(userSiteName))  {
			response.setHeader("errorMsg", auth.getName() + " is not authorized to view Site ID: " + site.getSiteName());
			if (logger.isWarnEnabled()) {
				logger.warn("!! UNAUTHORIZED ACCESS !!! " + auth.getName() + " is not authorized to view Site ID: " + site.getSiteName());
			}
			return null;
		}

		final Map<String, Object> model = new HashMap<String, Object>();
		final Map<String, Object> rows = new HashMap<String, Object>();

		rows.put("siteName", site.getSiteName());
		rows.put("orangeLeapUserId", site.getOrangeLeapUserId());
		rows.put("orangeLeapPassword", MASK);
		model.put("Results", 1);
		model.put("rows", rows);

		return new ModelAndView("jsonView", model);
	}

	private String resolveSiteName(final String userName) {
		return userName == null ? null : userName.substring(userName.indexOf('@') + 1);
	}
}
