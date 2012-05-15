package com.orangeleap.webtools.domain;

public class Session {
  String sessionId;
  Long   constituentId;
  Long accountNumber;

  public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Long getConstituentId() {
		return constituentId;
	}
	public void setConstituentId(Long constituentId) {
		this.constituentId = constituentId;
	}

  public Long getAccountNumber() {
    return accountNumber;
  }
	
  public void setAccountNumber(Long accountNumber) {
    this.accountNumber = accountNumber;
  }
	
}
