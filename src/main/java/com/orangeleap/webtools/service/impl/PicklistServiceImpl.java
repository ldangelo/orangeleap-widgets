package com.orangeleap.webtools.service.impl;

import com.orangeleap.client.GetPickListByNameRequest;
import com.orangeleap.client.GetPickListByNameResponse;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.Picklist;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.client.WSClient;
import com.orangeleap.webtools.service.PicklistService;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import javax.annotation.Resource;

@Service("picklistService")
public class PicklistServiceImpl implements PicklistService {
	private static final Log logger = LogFactory
			.getLog(PicklistServiceImpl.class);
	
  @Resource(name="picklistCache")
  Cache picklistCache;


  public List<PicklistItem> getPickListItems(String username, String password, String picklistname,boolean useCache)
  {

      Cache cache = (Cache) picklistCache;

      //
      // this is a greasy hack to get around the fact that the json parser
      // seems to barf on the name customFieldName[bank] LAD
      if (picklistname.equals("bank")) picklistname = "customFieldMap[bank]";
      
    if (useCache) {
      Element elem =  cache.get(resolveSiteName(username)+picklistname);
      if (elem!=null) {
        Picklist picklist = (Picklist) elem.getObjectValue();
        return picklist.getPicklistItems();
      }
    }

    WSClient wsClient = null;
    OrangeLeap oleap = null;
    
    wsClient = new WSClient();
    oleap = wsClient.getOrangeLeap(System.getProperty("webtools.wsdllocation"),username, password);
    GetPickListByNameRequest request = new GetPickListByNameRequest();
    GetPickListByNameResponse response = null;

    request.setName(picklistname);
    try {
    	response = oleap.getPickListByName(request);
    	if (response != null) {
          cache.put(new Element(resolveSiteName(username)+picklistname,response.getPicklist()));
    		return response.getPicklist().getPicklistItems();
    	}
    } catch (Exception e) {
    	logger.debug(picklistname + " " + e.getMessage());
    }

    return null;
  }
  
  public String resolveSiteName(final String userName) { 
    return userName == null ? null : userName.substring(userName.indexOf('@') + 1);
  }

  public void clearCache(String username) {
	  final List<String> keys = picklistCache.getKeys();
	  for (String key : keys) {
        if (key.startsWith(resolveSiteName(username))) {
			  picklistCache.remove(key);
		  }
	  }
  }
}
