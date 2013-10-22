package com.orangeleap.webtools.service.impl;

import java.util.List;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.WSClient;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.GetConstituentByIdRequest;
import com.orangeleap.client.GetConstituentByIdResponse;
import com.orangeleap.client.GetConstituentGiftRequest;
import com.orangeleap.client.GetConstituentGiftResponse;
import com.orangeleap.client.Gift;
import com.orangeleap.webtools.service.OrangeLeapClientService;
import com.orangeleap.webtools.service.SiteService;

import org.springframework.stereotype.Service;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Site;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetData;
import com.orangeleap.webtools.domain.WidgetExample;

@Service("orangeLeapClientService")
public class OrangeLeapClientServiceImpl implements OrangeLeapClientService {
  private static final Log logger = LogFactory.getLog(OrangeLeapClientServiceImpl.class);

  @Resource(name="constituentCache")
  Cache constituentCache;

  @Autowired
  WidgetDAO widgetDAO = null;

  @Autowired
  SiteService siteService;

  public void removeFromCache(String guid, Long id) {
    constituentCache.remove(guid + id);
  }

  public void clearCache(String guid) {
	  final List<String> keys = constituentCache.getKeys();
	  for (String key : keys) {
		  if (key.startsWith(guid)) {
			  constituentCache.remove(key);
		  }
	  }
  }
  
  public Constituent getConstituentById(String guid, Long id) {
    Element elem = constituentCache.get(guid+id);
    if (elem != null) {
      return (Constituent) elem.getObjectValue();
    }

    WidgetExample example = new WidgetExample();
    example.createCriteria().andWidgetGuidEqualTo(guid);
    example.createCriteria().andWidgetTypeEqualTo("customentity");
    List<Widget> widgets = widgetDAO.selectWidgetByExample(example);
    
    if (widgets.size() > 0) {

      //
      // guid is a unique key so this will only return one widget
      Widget widget = widgets.get(0);
      Site site = siteService.getSite(widgets.get(0).getSiteName());
      String wsusername = site.getOrangeLeapUserId();
      String wspassword = site.getOrangeLeapPassword();

      WSClient wsClient = null;
      OrangeLeap oleap = null;

      wsClient = new WSClient();
      oleap = wsClient.getOrangeLeap(System.getProperty("webtools.wsdllocation"),wsusername, wspassword);

      GetConstituentByIdRequest request = new GetConstituentByIdRequest();
      GetConstituentByIdResponse response = null;

      request.setId(id);

      response = oleap.getConstituentById(request);
      if (response != null) {
        Constituent c = response.getConstituent();
        constituentCache.put(new Element(guid+id,c));
        return response.getConstituent();
      }

    }

    return null;
  }

  public List<Gift> getConstituentGifts(String guid, Long constituentId) {
	  WidgetExample example = new WidgetExample();
	  example.createCriteria().andWidgetGuidEqualTo(guid);
	  List<Widget> widgets = widgetDAO.selectWidgetByExample(example);

	  if (widgets.size() > 0) {
    	
		  //
		  // guid is a unique key so this will only return one widget
		  Widget widget = widgets.get(0);

		  Site site = siteService.getSite(widgets.get(0).getSiteName());
		  String wsusername = site.getOrangeLeapUserId();
		  String wspassword = site.getOrangeLeapPassword();      

		  WSClient wsClient = null;
		  OrangeLeap oleap = null;

		  wsClient = new WSClient();
		  oleap = wsClient.getOrangeLeap(System.getProperty("webtools.wsdllocation"),wsusername, wspassword);

		  GetConstituentGiftRequest request = new GetConstituentGiftRequest();
		  GetConstituentGiftResponse response = null;

		  request.setConstituentId(constituentId);
		  request.setOffset(0);
		  request.setLimit(99);

		  response = oleap.getConstituentGift(request);

		  if (response != null) {
			  return response.getGift();
		  }

	  }
    return null;
  }
}