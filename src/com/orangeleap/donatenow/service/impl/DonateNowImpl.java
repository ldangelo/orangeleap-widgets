package com.orangeleap.donatenow.service.impl;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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
import com.orangeleap.donatenow.domain.Donation;
import com.orangeleap.donatenow.domain.PaymentStatus;
import com.orangeleap.donatenow.service.DonateNow;

public class DonateNowImpl implements DonateNow {
	private WSClient client;
	private OrangeLeap oleap;

	@Override
	public PaymentStatus donate(Donation d) {
		try {
			client = new WSClient();

			oleap = client
					.getOrangeLeap(
							"http://localhost:8080/orangeleap/services/1.0/orangeleap.wsdl",
							"nolan@company1", "ryan");

			Constituent c = findConstituent(d);
			if (c == null) {
				c = createConstituent(d);
			}

			Gift g = addGift(c, d);

			return createPaymentStatus(g);
		} catch (DatatypeConfigurationException ex) {
			return null;
		}
	}

	private Constituent findConstituent(Donation d) {
		SearchConstituentsRequest request = new SearchConstituentsRequest();
		request.setFirstName(d.getFirstName());
		request.setLastName(d.getLastName());

		Email email = new Email();
		CustomFieldMap fieldMap = new CustomFieldMap();
		email.setEmailAddress(d.getEmailAddress());
		email.setCustomFieldMap(fieldMap);

		request.setPrimaryEmail(email);

		SearchConstituentsResponse response = oleap.searchConstituents(request);
		List<Constituent> constituents = response.getConstituent();
		if (constituents.size() > 0)
			return constituents.get(0);
		else
			return null;
	}

	private Constituent createConstituent(Donation d) {
		CustomFieldMap fieldMap = new CustomFieldMap();
		CustomFieldMap cFieldMap = new CustomFieldMap();

		Constituent c = new Constituent();
		c.setFirstName(d.getFirstName());
		c.setLastName(d.getLastName());
		c.setCustomFieldMap(fieldMap);

		Email email = new Email();
		email.setEmailAddress(d.getEmailAddress());
		email.setCustomFieldMap(fieldMap);
		email.setPrimary(true);
		c.setPrimaryEmail(email);

		Phone phone = new Phone();
		phone.setNumber(d.getPhoneNumber());
		phone.setCustomFieldMap(fieldMap);
		phone.setPrimary(true);
		c.setPrimaryPhone(phone);

		Address address = new Address();
		address.setAddressLine1(d.getBillingAddress().getAddressLine1());
		address.setAddressLine2(d.getBillingAddress().getAddressLine2());
		address.setAddressLine3(d.getBillingAddress().getAddressLine3());
		address.setCity(d.getBillingAddress().getCity());
		address.setStateProvince(d.getBillingAddress().getState());
		address.setPostalCode(d.getBillingAddress().getPostalCode());
		address.setCustomFieldMap(fieldMap);
		address.setPrimary(true);
		address.setCountry("US");
		c.setPrimaryAddress(address);

		Site s = new Site();
		s.setActive(true);
		s.setName("company1");
		c.setSite(s);

		SaveOrUpdateConstituentRequest request = new SaveOrUpdateConstituentRequest();
		request.setConstituent(c);

		SaveOrUpdateConstituentResponse response = oleap
				.saveOrUpdateConstituent(request);
		return response.getConstituent();
	}

	private Gift addGift(Constituent c, Donation d)
			throws DatatypeConfigurationException {
		Gift g = new Gift();
		CustomFieldMap fieldMap = new CustomFieldMap();
		CustomFieldMap giftFieldMap = new CustomFieldMap();
		Entry e = new Entry();
		CustomField value = new CustomField();

		value.setDataType(0L);
		value.setEntityType("gift");
		value.setName("source");
		value.setValue("Online");

		e.setKey("source");
		e.setValue(value);
		giftFieldMap.getEntry().add(e);

		g.setConstituentId(c.getAccountNumber());
		g.setAmount(d.getAmount());
		g.setCustomFieldMap(giftFieldMap);
		g.setDeductibleAmount(d.getAmount());
		g.setDeductible(true);
		GregorianCalendar cal = new GregorianCalendar();
		g.setDonationDate(DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(cal));
		g.setGiftStatus("Pending");
		g.setPaymentStatus("");
		g.setAuthCode("");

		Site s = new Site();
		s.setActive(true);
		g.setSite(s);
		g.setSendAcknowledgment(true);
		g.setCurrencyCode("USD");

		DistributionLine dLine = new DistributionLine();
		dLine.setAmount(d.getAmount());
		dLine.setPercentage(new BigDecimal(100.00));
		dLine.setProjectCode("generalFundPresident");
		dLine.setCustomFieldMap(fieldMap);
		dLine.setConstituentId(c.getAccountNumber());
		g.getDistributionLines().add(dLine);

		if (d.getPaymentInfo().getPaymentType() == com.orangeleap.donatenow.domain.PaymentType.CC) {
			g.setPaymentType("Credit Card");

			PaymentSource source = new PaymentSource();
			source.setConstituentId(c.getAccountNumber());
			source.setCreditCardHolderName(d.getPaymentInfo().getName());
			source.setCreditCardNumber(d.getPaymentInfo().getCcNumber());
			source.setCreditCardExpirationMonth(d.getPaymentInfo().getCcExpMonth());
			source.setCreditCardExpirationYear(d.getPaymentInfo().getCcExpYear());
			source.setPaymentType(PaymentType.CREDIT_CARD);

			g.setPaymentSource(source);
		} else if (d.getPaymentInfo().getPaymentType() == com.orangeleap.donatenow.domain.PaymentType.ACH) {
			g.setPaymentType("ACH");
			PaymentSource source = new PaymentSource();
			source.setConstituentId(c.getAccountNumber());

			source.setAchAccountNumber(d.getPaymentInfo().getACHAccountNumber());
			source.setAchRoutingNumber(d.getPaymentInfo().getACHRoutingNumber());
			source.setAchHolderName(d.getPaymentInfo().getName());
			source.setPaymentType(PaymentType.ACH);
		}

		SaveOrUpdateGiftRequest request = new SaveOrUpdateGiftRequest();
		request.setGift(g);
		request.setConstituentId(c.getAccountNumber());
		SaveOrUpdateGiftResponse response = oleap.saveOrUpdateGift(request);

		if (response != null && response.getGift() != null) {
			return response.getGift();
		}

		return null;
	}

	private PaymentStatus createPaymentStatus(Gift g) {
		PaymentStatus status = new PaymentStatus();
		status.setAuthorizationCode(g.getAuthCode());
		status.setMessage(g.getPaymentMessage());
		if (g.getAuthCode() != null && g.getAuthCode() != "")
			status.setProcessed(true);
		return status;
	}
}
