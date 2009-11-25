package com.orangeleap.domain;

public class PaymentStatus {
	private String    autorizationCode;
	private Boolean   processed;
	private String    message;
	public String getAutorizationCode() {
		return autorizationCode;
	}
	public void setAutorizationCode(String autorizationCode) {
		this.autorizationCode = autorizationCode;
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
