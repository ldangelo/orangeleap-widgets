package com.orangeleap.webtools.service;

import java.util.List;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.Gift;
public interface OrangeLeapClientService {
  public List<Gift> getConstituentGifts(String guid, Long constituentId);
  public Constituent getConstituentById(String guid, Long id);
  public void clearCache(String guid);
  public void removeFromCache(String guid, Long id);
}