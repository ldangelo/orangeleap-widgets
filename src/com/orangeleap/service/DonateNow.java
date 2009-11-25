package com.orangeleap.service;

import javax.xml.datatype.DatatypeConfigurationException;

import com.orangeleap.domain.PaymentStatus;
import com.orangeleap.domain.Donation;

public interface DonateNow {

	public PaymentStatus donate(Donation d) throws DatatypeConfigurationException;
}
