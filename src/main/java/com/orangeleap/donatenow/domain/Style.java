package com.orangeleap.donatenow.domain;

import java.io.Serializable;

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

}