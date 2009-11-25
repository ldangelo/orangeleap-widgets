package com.orangeleap.domain;

public class CreditCardPaymentInfo implements PaymentInfo {
	
	private String ccNumber;
	private Integer ccExpMonth;
	private Integer ccExpYear;
	private String ccCCV;
	private Address billingAddress;
	private String ccName;
	
	public CreditCardPaymentInfo()
	{
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

	public void setName(String string) {
		this.ccName = string;
		
	}
	
	public String getName() {
		return this.ccName;
	}

}
