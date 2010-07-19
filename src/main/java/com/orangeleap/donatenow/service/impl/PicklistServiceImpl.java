package com.orangeleap.donatenow.service.impl;

import com.orangeleap.client.GetPickListByNameRequest;
import com.orangeleap.client.GetPickListByNameResponse;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.Picklist;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.client.WSClient;
import com.orangeleap.donatenow.service.PicklistService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import javax.annotation.Resource;

@Service("picklistService")
public class PicklistServiceImpl implements PicklistService {

  @Resource(name="picklistCache")
  Cache picklistCache;


  public List<PicklistItem> getPickListItems(String username, String password, String picklistname)
  {
    Cache cache = (Cache) picklistCache;

    Element elem =  cache.get(picklistname);
    if (elem!=null) {
      Picklist picklist = (Picklist) elem.getObjectValue();
      return picklist.getPicklistItems();
    }


    WSClient wsClient = null;
    OrangeLeap oleap = null;
    
    wsClient = new WSClient();
    oleap = wsClient.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),username, password);
    GetPickListByNameRequest request = new GetPickListByNameRequest();
    GetPickListByNameResponse response = null;

    request.setName(picklistname);
    response = oleap.getPickListByName(request);
    if (response != null) {
      cache.put(new Element(response.getPicklist().getPicklistName(),response.getPicklist()));
      return response.getPicklist().getPicklistItems();
    }

    return null;
  }
}