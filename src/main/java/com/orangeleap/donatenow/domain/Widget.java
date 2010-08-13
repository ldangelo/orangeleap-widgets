package com.orangeleap.donatenow.domain;

import java.io.Serializable;
import java.util.Date;

public class Widget implements Serializable {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.WIDGET_ID
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private Long widgetId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.WIDGET_GUID
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private String widgetGuid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.WIDGET_USERNAME
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private String widgetUsername;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.WIDGET_PASSWORD
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private String widgetPassword;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.WIDGET_ERROR_COUNT
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private Long widgetErrorCount;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.WIDGET_CREATE_DATE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private Date widgetCreateDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.WIDGET_HTML
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private String widgetHtml;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.WIDGET_VIEW_COUNT
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private Long widgetViewCount;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.WIDGET_TYPE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private String widgetType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.MOTIVATION_CODE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private String motivationCode;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.PROJECT_CODE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private String projectCode;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column WIDGET.CUSTOM_ENTITY_NAME
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private String customEntityName;


  private Boolean widgetAuthenticationRequired;
  private String     widgetAuthenticationURL;
  private String     sponsorshipURL;

  public final String getSponsorshipURL() {
    return sponsorshipURL;
  }

  public final void setSponsorshipURL(String url) {
    sponsorshipURL = url;
  }

  public final String getWidgetAuthenticationURL() {
    return widgetAuthenticationURL;
  }

  public void setWidgetAuthenticationURL(String url) {
    widgetAuthenticationURL = url;
  }

  public final Boolean getWidgetAuthenticationRequired() {
    return widgetAuthenticationRequired;
  }

  public final void setWidgetAuthenticationRequired(Boolean b) {
    widgetAuthenticationRequired = b;
  }

  /**
   * Get the <code>WidgetLoginSuccessURL</code> value.
   *
   * @return a <code>String</code> value
   */
  public final String getWidgetLoginSuccessURL() {
    return widgetLoginSuccessURL;
  }

  /**
   * Set the <code>WidgetLoginSuccessURL</code> value.
   *
   * @param widgetLoginSuccessURL The new WidgetLoginSuccessURL value.
   */
  public final void setWidgetLoginSuccessURL(final String widgetLoginSuccessURL) {
    this.widgetLoginSuccessURL = widgetLoginSuccessURL;
  }

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private static final long serialVersionUID = 1L;
  /**
   * Describe widgetLoginSuccessURL here.
   */
  private String widgetLoginSuccessURL;
  /**
   * Describe widgetLoginFailureURL here.
   */
  private String widgetLoginFailureURL;

  /**
   * Get the <code>WidgetLoginFailureURL</code> value.
   *
   * @return a <code>String</code> value
   */
  public final String getWidgetLoginFailureURL() {
    return widgetLoginFailureURL;
  }

  /**
   * Set the <code>WidgetLoginFailureURL</code> value.
   *
   * @param widgetLoginFailureURL The new WidgetLoginFailureURL value.
   */
  public final void setWidgetLoginFailureURL(final String widgetLoginFailureURL) {
    this.widgetLoginFailureURL = widgetLoginFailureURL;
  }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.WIDGET_ID
     *
     * @return the value of WIDGET.WIDGET_ID
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public Long getWidgetId() {
        return widgetId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.WIDGET_ID
     *
     * @param widgetId the value for WIDGET.WIDGET_ID
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setWidgetId(Long widgetId) {
        this.widgetId = widgetId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.WIDGET_GUID
     *
     * @return the value of WIDGET.WIDGET_GUID
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public String getWidgetGuid() {
        return widgetGuid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.WIDGET_GUID
     *
     * @param widgetGuid the value for WIDGET.WIDGET_GUID
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setWidgetGuid(String widgetGuid) {
        this.widgetGuid = widgetGuid == null ? null : widgetGuid.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.WIDGET_USERNAME
     *
     * @return the value of WIDGET.WIDGET_USERNAME
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public String getWidgetUsername() {
        return widgetUsername;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.WIDGET_USERNAME
     *
     * @param widgetUsername the value for WIDGET.WIDGET_USERNAME
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setWidgetUsername(String widgetUsername) {
        this.widgetUsername = widgetUsername == null ? null : widgetUsername.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.WIDGET_PASSWORD
     *
     * @return the value of WIDGET.WIDGET_PASSWORD
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public String getWidgetPassword() {
        return widgetPassword;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.WIDGET_PASSWORD
     *
     * @param widgetPassword the value for WIDGET.WIDGET_PASSWORD
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setWidgetPassword(String widgetPassword) {
        this.widgetPassword = widgetPassword == null ? null : widgetPassword.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.WIDGET_ERROR_COUNT
     *
     * @return the value of WIDGET.WIDGET_ERROR_COUNT
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public Long getWidgetErrorCount() {
        return widgetErrorCount;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.WIDGET_ERROR_COUNT
     *
     * @param widgetErrorCount the value for WIDGET.WIDGET_ERROR_COUNT
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setWidgetErrorCount(Long widgetErrorCount) {
        this.widgetErrorCount = widgetErrorCount;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.WIDGET_CREATE_DATE
     *
     * @return the value of WIDGET.WIDGET_CREATE_DATE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public Date getWidgetCreateDate() {
        return widgetCreateDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.WIDGET_CREATE_DATE
     *
     * @param widgetCreateDate the value for WIDGET.WIDGET_CREATE_DATE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setWidgetCreateDate(Date widgetCreateDate) {
        this.widgetCreateDate = widgetCreateDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.WIDGET_HTML
     *
     * @return the value of WIDGET.WIDGET_HTML
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public String getWidgetHtml() {
        return widgetHtml;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.WIDGET_HTML
     *
     * @param widgetHtml the value for WIDGET.WIDGET_HTML
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setWidgetHtml(String widgetHtml) {
        this.widgetHtml = widgetHtml == null ? null : widgetHtml.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.WIDGET_VIEW_COUNT
     *
     * @return the value of WIDGET.WIDGET_VIEW_COUNT
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public Long getWidgetViewCount() {
        return widgetViewCount;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.WIDGET_VIEW_COUNT
     *
     * @param widgetViewCount the value for WIDGET.WIDGET_VIEW_COUNT
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setWidgetViewCount(Long widgetViewCount) {
        this.widgetViewCount = widgetViewCount;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.WIDGET_TYPE
     *
     * @return the value of WIDGET.WIDGET_TYPE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public String getWidgetType() {
        return widgetType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.WIDGET_TYPE
     *
     * @param widgetType the value for WIDGET.WIDGET_TYPE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType == null ? null : widgetType.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.MOTIVATION_CODE
     *
     * @return the value of WIDGET.MOTIVATION_CODE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public String getMotivationCode() {
        return motivationCode;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.MOTIVATION_CODE
     *
     * @param motivationCode the value for WIDGET.MOTIVATION_CODE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setMotivationCode(String motivationCode) {
        this.motivationCode = motivationCode == null ? null : motivationCode.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.PROJECT_CODE
     *
     * @return the value of WIDGET.PROJECT_CODE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.PROJECT_CODE
     *
     * @param projectCode the value for WIDGET.PROJECT_CODE
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column WIDGET.CUSTOM_ENTITY_NAME
     *
     * @return the value of WIDGET.CUSTOM_ENTITY_NAME
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public String getCustomEntityName() {
        return customEntityName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column WIDGET.CUSTOM_ENTITY_NAME
     *
     * @param customEntityName the value for WIDGET.CUSTOM_ENTITY_NAME
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setCustomEntityName(String customEntityName) {
        this.customEntityName = customEntityName == null ? null : customEntityName.trim();
    }
}