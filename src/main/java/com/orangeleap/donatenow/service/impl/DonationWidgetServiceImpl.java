package com.orangeleap.donatenow.service.impl;

import java.util.Date;
import com.orangeleap.client.*;
import com.orangeleap.donatenow.dao.DonationWidgetDAO;
import com.orangeleap.donatenow.domain.DonationWidget;
import com.orangeleap.donatenow.domain.DonationWidgetExample;
import com.orangeleap.donatenow.service.DonationWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service("donationWidgetService")
public class DonationWidgetServiceImpl implements DonationWidgetService {
  private static final Log logger = LogFactory.getLog(DonationWidgetServiceImpl.class);
  private WSClient wsClient = null;
  private OrangeLeap oleap = null;

  @Autowired
  DonationWidgetDAO donationWidgetDAO;

  public DonationWidget create() {
    return new DonationWidget();
  }

  public DonationWidget findWidgetById(Long id) {
    return donationWidgetDAO.selectDonationWidgetByPrimaryKey(id);
  }

  private OrangeLeap getOrangeLeap() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
        if (oleap == null) {
          String userName = auth.getName();
          String password = (String) auth.getCredentials();   	
          wsClient = new WSClient();
          oleap = wsClient.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),userName, password);
        }
        return oleap;
  }

  public DonationWidget setProjectCode(DonationWidget widget) {
    OrangeLeap oleap = getOrangeLeap();
    GetPickListByNameRequest request = new GetPickListByNameRequest();
    GetPickListByNameResponse response = null;
		
		request.setName("projectCode");

		try {
		    response = oleap.getPickListByName(request);		    
		} catch ( Exception ex) {
		    logger.error(ex.getMessage());
		}


		widget.setProjectCodeList(response.getPicklist().getPicklistItems());
        return widget;
}

  public DonationWidget setMotivationCode(DonationWidget widget) {
		GetPickListByNameRequest request = new GetPickListByNameRequest();
		GetPickListByNameResponse response = null;
		request.setName("motivationCode");


		try {
		    response = oleap.getPickListByName(request);		    
		} catch ( Exception ex) {
		    logger.error(ex.getMessage());
		}

		widget.setMotivationCodeList(response.getPicklist().getPicklistItems());		
        return widget;
  }

  public DonationWidget saveOrUpdate(DonationWidget widget) {
	if (widget.getWidgetCreateDate() == null)
	    widget.setWidgetCreateDate(new Date());
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	String userName = auth.getName();
	String password = (String) auth.getCredentials();
	String siteName = userName.substring(userName.indexOf('@') + 1);
	widget.setWidgetUsername(userName);
	widget.setWidgetPassword(password);

	
		
	if (widget.getWidgetId() != null)
		donationWidgetDAO.updateDonationWidgetByPrimaryKey(widget);
	else
		donationWidgetDAO.insertDonationWidget(widget);
	
    return widget;
  }
}