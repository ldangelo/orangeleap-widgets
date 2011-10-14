package com.orangeleap.webtools.service;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.Gift;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.webtools.domain.WidgetData;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;

public interface OrangeLeapWidgetService {
  public List<Gift> getConstituentGifts(String guid, String sessionId);

  public String authenticate(String guid, String username, String password);

  public String getRoles();

  public WidgetData create();

  public WidgetData process(WidgetData d) throws DatatypeConfigurationException;

  public void updateViewCount(String guid, String referrer);

  public Constituent getConstituent(String guid,String sessionId);

  public CustomTableRow addCustomTableRow(String guid,Entry[] row);
  
  public String changePassword(String guid,String username, String oldpassword, String newpassword);
  
  public String forgotPassword(String guid,String username);

  public String sendGiftReceipt(Long giftId,String sessionId, String guid);
}
