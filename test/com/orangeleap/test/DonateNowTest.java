package com.orangeleap.test;


import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.orangeleap.domain.Address;
import com.orangeleap.domain.CreditCardPaymentInfo;
import com.orangeleap.domain.Donation;
import com.orangeleap.domain.PaymentStatus;
import com.orangeleap.service.impl.DonateNowImpl;

public class DonateNowTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_DonateNow() throws DatatypeConfigurationException {
		Donation d = new Donation();
		d.setFirstName("Jill");
		d.setLastName("DAngelo");
		d.setEmailAddress("ldangelo@orangeleap.com");
		d.setPhoneNumber("9729790116");
		d.setAmount(new BigDecimal(15.00));
		
		Address a = new Address();
		a.setAddressLine1("5108 Marble Falls Lane");
		a.setAddressLine2("");
		a.setAddressLine3("");
		a.setCity("");
		a.setState("TX");
		a.setPostalCode("75093");
		d.setBillingAddress(a);
		
		CreditCardPaymentInfo payInfo = new CreditCardPaymentInfo();
		payInfo.setName("Leo DAngelo");
		payInfo.setCcNumber("4111111111111111");
		payInfo.setCcExpMonth(12);
		payInfo.setCcExpYear(2012);
		d.setPaymentInfo(payInfo);
		
		DonateNowImpl donate = new DonateNowImpl();
		PaymentStatus status = donate.donate(d);
		
		assertTrue(status != null);
	}
	
	@Test
	public void test_NewConstituent() throws DatatypeConfigurationException 
	{
		Donation d = new Donation();

		d.setFirstName("Amy");
		d.setLastName("DAngelo");
		d.setEmailAddress("ldangelo@orangeleap.com");
		d.setPhoneNumber("9729790116");
		d.setAmount(new BigDecimal(15.00));

		Address a = new Address();
		a.setAddressLine1("5108 Marble Falls Lane");
		a.setAddressLine2("");
		a.setAddressLine3("");
		a.setCity("");
		a.setState("TX");
		a.setPostalCode("75093");
		d.setBillingAddress(a);
		
		CreditCardPaymentInfo payInfo = new CreditCardPaymentInfo();
		payInfo.setName("Leo DAngelo");
		payInfo.setCcNumber("4111111111111111");
		payInfo.setCcExpMonth(12);
		payInfo.setCcExpYear(2012);
		d.setPaymentInfo(payInfo);
		
		DonateNowImpl donate = new DonateNowImpl();
		PaymentStatus status = donate.donate(d);
		
		assertTrue(status != null);
	}
}
