package com.orangeleap.donatenow.service;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.Gift;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.donatenow.domain.WidgetData;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;

public interface OrangeLeapWidgetService {
  public List<Gift> getConstituentGifts(String guid, Long constituentId);

  public Long authenticate(String guid, String username, String password);

  public WidgetData create();

  public WidgetData process(WidgetData d) throws DatatypeConfigurationException;

  public void updateViewCount(String guid, String referrer);

  public Constituent getConstituent(String guid,Long id);

  public CustomTableRow addCustomTableRow(String guid,Entry[] row);
}
