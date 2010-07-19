package com.orangeleap.donatenow.service;

import com.orangeleap.client.Constituent;

public interface OrangeLeapClientService {
  public Constituent getConstituentById(String guid, Long id);
  public void removeFromCache(Long id);
}