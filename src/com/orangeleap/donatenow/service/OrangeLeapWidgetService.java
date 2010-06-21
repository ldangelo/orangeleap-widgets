package com.orangeleap.donatenow.service;

import com.orangeleap.client.Constituent;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.Gift;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.donatenow.domain.WidgetData;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;

public interface OrangeLeapWidgetService {
  public List<Gift> getConstituentGifts(String guid, Long constituentId);

  public List<PicklistItem> getPickListItems(String guid,String picklistname);

  public Long authenticate(String guid, String username, String password);

  public WidgetData create();

  public WidgetData process(WidgetData d) throws DatatypeConfigurationException;

  public CustomTable getCustomEntity(String guid);

  public void updateViewCount(String guid, String referrer);

  public Constituent getConstituent(String guid,Long id);
}
