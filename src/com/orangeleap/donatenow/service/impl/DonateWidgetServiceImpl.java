package com.orangeleap.donatenow.service.impl;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orangeleap.client.Address;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.CustomField;
import com.orangeleap.client.DistributionLine;
import com.orangeleap.client.Email;
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
import com.orangeleap.donatenow.dao.PlacementDao;
import com.orangeleap.donatenow.domain.DonateWidget;
import com.orangeleap.donatenow.domain.Donation;
import com.orangeleap.donatenow.domain.PaymentStatus;
import com.orangeleap.donatenow.domain.Placement;
import com.orangeleap.donatenow.service.DonateWidgetService;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

@Service("donateWidgetService")
public class DonateWidgetServiceImpl implements DonateWidgetService {
	@Autowired
	protected DonateWidgetDao donateWidgetDao = null;

	@Autowired
	protected PlacementDao placementDao;

	private WSClient client;
	private OrangeLeap oleap;

	@Override
	public List<DonateWidget> listWidgets(String userName, String password)
	{
		   List<DonateWidget> widgets = donateWidgetDao.listWidgets(userName, password);
		   return widgets;
	}
	
	@Override
	public PaymentStatus createWidget(Donation d)
			throws DatatypeConfigurationException {
		DonateWidget widget = donateWidgetDao.findWidgetByGuid(d.getGUID());
		if (widget != null) {
			client = new WSClient();

			oleap = client
					.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),widget.getUserName(), widget.getPassWord());

			String site = widget.getUserName().substring(widget.getUserName().indexOf("@"));
			Constituent c = findConstituent(d);
			if (c == null) {
				c = createConstituent(d);
			}

			Gift g = addGift(c, d, widget);
			
			PaymentStatus status = createPaymentStatus(g);
			
			//
			// see if a placement exists for this referrer....
			Placement placement = placementDao.findPlacementByURL(d.getReferrer(),site);
			if (placement == null) {
					placement = new Placement();
					placement.setWidgetid(widget.getId());
					placement.setPlacementURL(d.getReferrer());
					placement.setErrorCount(0L);
					placement.setGiftCount(0L);
					placement = placementDao.save(placement);
			}
			if (status.getAuthorizationCode() != null && status.getAuthorizationCode() != "") {
				widget.setGiftCount(widget.getGiftCount() + 1);
				placement.setGiftCount(placement.getGiftCount() + 1);
				donateWidgetDao.update(widget);
				placementDao.update(placement);
			} else {
				widget.setErrorCount(widget.getErrorCount() + 1);
				placement.setErrorCount(placement.getErrorCount() + 1);
				donateWidgetDao.update(widget);
				placementDao.update(placement);
			}
			return status; 
		}
		
		return null;

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
		c.setConstituentType("individual");
		
		Email email = new Email();
		email.setEmailAddress(d.getEmailAddress());
		email.setCustomFieldMap(fieldMap);
		email.setPrimary(true);
		c.getEmails().add(email);


		Phone phone = new Phone();
		phone.setNumber(d.getPhoneNumber());
		phone.setCustomFieldMap(fieldMap);
		phone.setPrimary(true);
		c.getPhones().add(phone);


		/*
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
*/
		Site s = new Site();

		s.setName("company1");


		SaveOrUpdateConstituentRequest request = new SaveOrUpdateConstituentRequest();
		request.setConstituent(c);

		SaveOrUpdateConstituentResponse response = oleap
				.saveOrUpdateConstituent(request);
		return response.getConstituent();
	}

	private Gift addGift(Constituent c, Donation d, DonateWidget widget)
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
		String siteName = widget.getUserName().substring(widget.getUserName().indexOf('@') + 1);
		s.setName(siteName);
		GregorianCalendar today = new GregorianCalendar();
		XMLGregorianCalendar xmlToday = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		

		g.setSendAcknowledgment(true);
		g.setCurrencyCode("USD");
		g.setCreateDate(xmlToday);
		DistributionLine dLine = new DistributionLine();
		dLine.setAmount(d.getAmount());
		dLine.setPercentage(new BigDecimal(100.00));
		dLine.setProjectCode(widget.getProjectCode());
		dLine.setMotivationCode(widget.getMotivationCode());
		dLine.setCustomFieldMap(fieldMap);
		dLine.setConstituentId(c.getAccountNumber());
		g.getDistributionLines().add(dLine);

		if (d.getPaymentInfo().getPaymentType() == com.orangeleap.donatenow.domain.PaymentType.CC) {
			g.setPaymentType(PaymentType.CREDIT_CARD);


			PaymentSource source = new PaymentSource();
			source.setConstituentId(c.getAccountNumber());
			source.setCreditCardHolderName(d.getPaymentInfo().getName());
			source.setCreditCardNumber(d.getPaymentInfo().getCcNumber());
			source.setCreditCardExpirationMonth(d.getPaymentInfo()
					.getCcExpMonth());
			source.setCreditCardExpirationYear(d.getPaymentInfo()
					.getCcExpYear());
			source.setPaymentType(PaymentType.CREDIT_CARD);
			source.setCreditCardType("Visa");
			g.setPaymentSource(source);
		} else if (d.getPaymentInfo().getPaymentType() == com.orangeleap.donatenow.domain.PaymentType.ACH) {
			g.setPaymentType(PaymentType.ACH);
			PaymentSource source = new PaymentSource();
			source.setConstituentId(c.getAccountNumber());

			source
					.setAchAccountNumber(d.getPaymentInfo()
							.getACHAccountNumber());
			source
					.setAchRoutingNumber(d.getPaymentInfo()
							.getACHRoutingNumber());
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

	@Override
	public void updateViewCount(String guid, String referrer) {
		DonateWidget widget = donateWidgetDao.findWidgetByGuid(guid);

		String site = widget.getUserName().substring(widget.getUserName().indexOf("@"));
		Placement placement = placementDao.findPlacementByURL(referrer,site);
		
		if (widget == null) return;
		
		//
		// there are no placements for this widget yet.
		if (placement == null) {
					placement = new Placement();
					placement.setWidgetid(widget.getId());
					placement.setPlacementURL(referrer);
					placement.setErrorCount(0L);
					placement.setGiftCount(0L);
					placement.setViewCount(1L);
					placement = placementDao.save(placement);
		} else {
			placement.setViewCount(placement.getViewCount() + 1);
			placementDao.update(placement);
		}
		
		if (widget != null)  { 
			widget.setViewCount(widget.getViewCount() + 1);
			donateWidgetDao.update(widget);
		}
	}
}
