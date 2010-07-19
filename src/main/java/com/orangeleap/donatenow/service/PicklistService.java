package com.orangeleap.donatenow.service;

import java.util.List;
import com.orangeleap.client.PicklistItem;

public interface PicklistService {
  public List<PicklistItem> getPickListItems(String username, String password, String picklisname);
}