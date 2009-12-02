package com.orangeleap.donatenow.service;

import javax.xml.datatype.DatatypeConfigurationException;

import com.orangeleap.donatenow.domain.Donation;
import com.orangeleap.donatenow.domain.PaymentStatus;

public interface DonateNow {

	public PaymentStatus donate(Donation d) throws DatatypeConfigurationException;
}
