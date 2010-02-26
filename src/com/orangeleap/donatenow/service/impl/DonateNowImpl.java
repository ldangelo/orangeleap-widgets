package com.orangeleap.donatenow.service.impl;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.orangeleap.client.Address;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.CustomField;
import com.orangeleap.client.DistributionLine;
import com.orangeleap.client.Email;
import com.orangeleap.client.FindConstituentsRequest;
import com.orangeleap.client.FindConstituentsResponse;
import com.orangeleap.client.Gift;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.PaymentSource;
import com.orangeleap.client.PaymentType;
import com.orangeleap.client.Phone;
import com.orangeleap.client.SaveOrUpdateConstituentRequest;
import com.orangeleap.client.SaveOrUpdateConstituentResponse;
import com.orangeleap.client.SaveOrUpdateGiftRequest;
import com.orangeleap.client.SaveOrUpdateGiftResponse;
import com.orangeleap.client.SearchConstituentsRequest;
import com.orangeleap.client.SearchConstituentsResponse;
import com.orangeleap.client.Site;
import com.orangeleap.client.WSClient;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry;
import com.orangeleap.donatenow.dao.DonateWidgetDao;
import com.orangeleap.donatenow.domain.DonateWidget;
import com.orangeleap.donatenow.domain.Donation;
import com.orangeleap.donatenow.domain.PaymentStatus;
import com.orangeleap.donatenow.service.DonateNow;
import com.orangeleap.donatenow.service.DonateWidgetService;

public class DonateNowImpl implements DonateNow {

	@Autowired
	DonateWidgetService donateWidgetService;
	    
	@Override
	public void updateViewCount(String guid, String referrer) {
		donateWidgetService.updateViewCount(guid,referrer);
	}
	
	@Override
	public PaymentStatus donate(Donation d) {
		try {
			return donateWidgetService.createWidget(d);
		} catch (DatatypeConfigurationException ex) {
			return null;
		}
	}


}
