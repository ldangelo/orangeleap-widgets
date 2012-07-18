package com.orangeleap.webtools.service;

import java.util.List;
import com.orangeleap.client.PicklistItem;

public interface PicklistService {
  public List<PicklistItem> getPickListItems(String username, String password, String picklisname);
  public void clearCache(String username);
}