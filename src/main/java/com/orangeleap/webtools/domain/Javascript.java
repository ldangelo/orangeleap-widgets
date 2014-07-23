package com.orangeleap.webtools.domain;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class Javascript implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5867271306198782606L;
	private Long javascriptId;
	private String javascript;
	private String javascriptName;
	private boolean inactive;
	private boolean deleted;
	private String userName;
	private String siteName;

	public final Long getJavascriptId() {
		return javascriptId;
	}

	public final void setJavascriptId(final Long javascriptId) {
		this.javascriptId = javascriptId;
	}

	public final String getJavascript() {
		return javascript;
	}

	public final void setJavascript(final String javascript) {
		this.javascript = javascript;
	}
	
	public String getJavascriptName() {
		return javascriptName;
	}

	public void setJavascriptName(String javascriptName) {
		this.javascriptName = javascriptName;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(final boolean inactive) {
		this.inactive = inactive;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(final boolean deleted) {
		this.deleted = deleted;
	}

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(final String userName) {
		this.userName = userName;

		if (StringUtils.isNotBlank(userName) && userName.indexOf('@') > -1 && StringUtils.isEmpty(siteName)) {
			final String[] tokens = userName.split("@");
			if (tokens.length == 2) {
				setSiteName(tokens[1]);
			}
		}
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(final String siteName) {
		this.siteName = siteName;
	}
}