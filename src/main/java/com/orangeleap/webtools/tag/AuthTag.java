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

package com.orangeleap.webtools.tag;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

public class AuthTag extends RequestContextAwareTag {

	protected int doStartTagInternal() throws Exception {

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String userName = auth.getName();

		pageContext.getRequest().setAttribute("donorWidgetsUserName", userName);
		return EVAL_BODY_INCLUDE;
	}
}