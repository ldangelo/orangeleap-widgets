package com.orangeleap.donatenow.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

import com.orangeleap.client.GetPickListByNameRequest;
import com.orangeleap.client.GetPickListByNameResponse;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.client.WSClient;

public class DonateWidget {
    private Long   id;
    private String   guid;
    private String userName;
    private String passWord;
    private Long   giftCount;
    private Long   errorCount;
    private Date   createDate;
    private String projectCode;
    private String motivationCode;
    private String widgetHTML;
    private List<PicklistItem> projectCodeList;
    private List<PicklistItem> motivationCodeList;
    private Long  viewCount;
    private String site;

	public DonateWidget() {
		guid = UUID.randomUUID().toString();
		userName = null;
		passWord = null;
		giftCount = 0L;
		errorCount = 0L;
		widgetHTML = "";
		viewCount = 0L;
	}

	public DonateWidget(DonateWidget w) {
		guid = w.getGuid();
		userName = w.getUserName();
		passWord = w.getPassWord();
		giftCount = w.getGiftCount();
		errorCount = w.getErrorCount();
		widgetHTML = w.getWidgetHTML();
		projectCodeList = w.getProjectCodeList();
		motivationCodeList = w.getMotivationCodeList();
		viewCount = w.getViewCount();
	}
	public List<PicklistItem> getProjectCodeList() {
		return projectCodeList;
	}

	public void setProjectCodeList(List<PicklistItem> projectCodeList) {
		this.projectCodeList = projectCodeList;
	}

	public List<PicklistItem> getMotivationCodeList() {
		return motivationCodeList;
	}

	public void setMotivationCodeList(List<PicklistItem> motivationCodeList) {
		this.motivationCodeList = motivationCodeList;
	}

	DonateWidget(String guid, String userName, String passWord, Long giftCount, Long errorCount,String widgetHTML,List<PicklistItem> projectCodeList,List<PicklistItem> motivationCodeList)
	{
		this.guid = guid;
		this.userName = userName;
		this.passWord = passWord;
		this.giftCount = giftCount;
		this.errorCount = errorCount;
		this.widgetHTML = widgetHTML;
		this.motivationCodeList = motivationCodeList;
		this.projectCodeList = projectCodeList;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Long getGiftCount() {
		return giftCount;
	}

	public void setGiftCount(Long giftCount) {
		this.giftCount = giftCount;
	}

	public Long getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Long errorCount) {
		this.errorCount = errorCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getMotivationCode() {
		return motivationCode;
	}

	public void setMotivationCode(String motivationCode) {
		this.motivationCode = motivationCode;
	}

	public String getWidgetHTML() {
		return widgetHTML;
	}

	public void setWidgetHTML(String widgetHTML) {
		this.widgetHTML = widgetHTML;
	}

	public Date getCreated() {
		// TODO Auto-generated method stub
		return this.createDate;
	}
	
	public void setCreated(Date d) {
		this.createDate = d;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getViewCount() {

		return this.viewCount;
	}
	
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
	
	
}
