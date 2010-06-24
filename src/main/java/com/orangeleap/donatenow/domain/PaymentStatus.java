package com.orangeleap.donatenow.domain;

public class PaymentStatus {
	private String    authorizationCode;
	private Boolean   processed;
	private String    message;
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String autorizationCode) {
		this.authorizationCode = autorizationCode;
	}
	public Boolean getProcessed() {
		return processed;
	}
	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
