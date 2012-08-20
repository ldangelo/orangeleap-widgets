/*
 * Copyright (c) 2009. Orange Leap Inc. Active Constituent
 * Relationship Management Platform.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.orangeleap.webtools.controller.json;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.service.WidgetService;
import net.sf.ehcache.Cache;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

@Controller
public class LogoutController {

	@Autowired
	private WidgetService widgetService;

	@Resource(name="sessionCache")
	private Cache sessionCache;

	@RequestMapping("/logout.json")
	public void logout(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		final String guid = request.getParameter("guid");

		final String sessionId = request.getParameter("sessionId");

		if (StringUtils.isNotBlank(sessionId)) {
			final Cookie sessionCookie = WebUtils.getCookie(request, "sessionId");
			if (sessionCookie != null) {
				sessionCookie.setMaxAge(0);
				sessionCookie.setValue("");

				response.addCookie(sessionCookie);
			}
			sessionCache.remove(sessionId);
		}

		final Widget widget = widgetService.getWidget(guid);
		final String authenticationUrl = widget.getWidgetAuthenticationURL();

		response.sendRedirect(authenticationUrl);
	}
}
