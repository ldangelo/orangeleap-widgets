package com.orangeleap.donatenow.service;

import com.orangeleap.donatenow.domain.DonationWidget;

public interface DonationWidgetService {
  DonationWidget create();
  DonationWidget findWidgetById(Long id);
  DonationWidget setProjectCode(DonationWidget widget);
  DonationWidget setMotivationCode(DonationWidget widget);
  DonationWidget saveOrUpdate(DonationWidget widget);
}