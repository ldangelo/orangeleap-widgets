package com.orangeleap.domain;

public class ACHPaymentInfo implements PaymentInfo {
	private String ACHRoutingNumber;
	private String ACHAccountNumber;
	private String ACHName;
	public String getACHRoutingNumber() {
		return ACHRoutingNumber;
	}
	public void setACHRoutingNumber(String aCHRoutingNumber) {
		ACHRoutingNumber = aCHRoutingNumber;
	}
	public String getACHAccountNumber() {
		return ACHAccountNumber;
	}
	public void setACHAccountNumber(String aCHAccountNumber) {
		ACHAccountNumber = aCHAccountNumber;
	}
	public String getACHName() {
		return ACHName;
	}
	public void setACHName(String aCHName) {
		ACHName = aCHName;
	}
	
}
