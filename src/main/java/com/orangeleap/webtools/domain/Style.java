package com.orangeleap.webtools.domain;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class Style implements Serializable {
	/**
	 * Describe Id here.
	 */
	private Long Id;
	/**
	 * Describe UserName here.
	 */
	private String UserName;
	/**
	 * Describe Style here.
	 */
	private String Style;

	private String StyleName;

	private boolean inactive;
	private boolean deleted;
	private String siteName;

	public String getStyleName() {
		return StyleName;
	}

	public void setStyleName(String styleName) {
		StyleName = styleName;
	}

	/**
	 * Get the <code>Style</code> value.
	 *
	 * @return a <code>String</code> value
	 */
	public final String getStyle() {
		return Style;
	}

	/**
	 * Set the <code>Style</code> value.
	 *
	 * @param Style The new Style value.
	 */
	public final void setStyle(final String Style) {
		this.Style = Style;
	}

	/**
	 * Get the <code>UserName</code> value.
	 *
	 * @return a <code>String</code> value
	 */
	public final String getUserName() {
		return UserName;
	}

	/**
	 * Set the <code>UserName</code> value.
	 *
	 * @param UserName The new UserName value.
	 */
	public final void setUserName(final String UserName) {
		this.UserName = UserName;

		if (StringUtils.isNotBlank(UserName) && UserName.indexOf('@') > -1 && StringUtils.isEmpty(siteName)) {
			final String[] tokens = UserName.split("@");
			if (tokens.length == 2) {
				setSiteName(tokens[1]);
			}
		}
	}

	/**
	 * Get the <code>Id</code> value.
	 *
	 * @return a <code>Long</code> value
	 */
	public final Long getId() {
		return Id;
	}

	/**
	 * Set the <code>Id</code> value.
	 *
	 * @param Id The new Id value.
	 */
	public final void setId(final Long Id) {
		this.Id = Id;
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

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(final String siteName) {
		this.siteName = siteName;
	}
}