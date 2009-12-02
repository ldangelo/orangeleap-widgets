package com.orangeleap.donatenow.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.orangeleap.donatenow.domain.Donation;
import com.orangeleap.donatenow.domain.PaymentStatus;

public class DonateNow extends SimpleFormController {

	private com.orangeleap.donatenow.service.DonateNow donateNow;
	
	public ModelAndView onSubmit(Object command) throws ServletException {
		Donation donate = (Donation) command;
	
		try {
			PaymentStatus status = donateNow.donate(donate);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		Donation donate = new Donation();
		
		return donate;
	}

	public com.orangeleap.donatenow.service.DonateNow getDonateNow() {
		return donateNow;
	}

	public void setDonateNow(com.orangeleap.donatenow.service.DonateNow donateNow) {
		this.donateNow = donateNow;
	}
}
