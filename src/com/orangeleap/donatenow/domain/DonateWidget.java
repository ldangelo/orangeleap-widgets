package com.orangeleap.donatenow.domain;

import java.util.UUID;

public class DonateWidget {
	UUID guid;
	String userName;
	String passWord;
	Long   giftCount;
	Long   errorCount;
	
	DonateWidget() {
		guid = UUID.randomUUID();
		userName = null;
		passWord = null;
		giftCount = 0L;
		errorCount = 0L;
	}
	
	DonateWidget(UUID guid, String userName, String passWord, Long giftCount, Long errorCount)
	{
		this.guid = guid;
		this.userName = userName;
		this.passWord = passWord;
		this.giftCount = giftCount;
		this.errorCount = errorCount;
	}

	public UUID getGuid() {
		return guid;
	}

	public void setGuid(UUID guid) {
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
	
	
}
