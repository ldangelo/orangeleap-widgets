package com.orangeleap.donatenow.service;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import com.orangeleap.donatenow.domain.DonateWidget;
import com.orangeleap.donatenow.domain.Donation;
import com.orangeleap.donatenow.domain.PaymentStatus;

public interface DonateWidgetService {
	public PaymentStatus createWidget(Donation donation) throws DatatypeConfigurationException;

	List<DonateWidget> listWidgets(String userName, String password);

	
	public void updateViewCount(String guid, String refererrer);
}
