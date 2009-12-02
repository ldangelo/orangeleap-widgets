package com.orangeleap.donatenow.domain;

public class PaymentInfo {
	private PaymentType paymentType;
	private String ccNumber;
	private Integer ccExpMonth;
	private Integer ccExpYear;
	private String ccCCV;
	private Address billingAddress;
	private String Name;
	private String ACHRoutingNumber;
	private String ACHAccountNumber;

	public PaymentInfo() {
		
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public Integer getCcExpMonth() {
		return ccExpMonth;
	}

	public void setCcExpMonth(Integer ccExpMonth) {
		this.ccExpMonth = ccExpMonth;
	}

	public Integer getCcExpYear() {
		return ccExpYear;
	}

	public void setCcExpYear(Integer ccExpYear) {
		this.ccExpYear = ccExpYear;
	}

	public String getCcCCV() {
		return ccCCV;
	}

	public void setCcCCV(String ccCCV) {
		this.ccCCV = ccCCV;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

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

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
}
