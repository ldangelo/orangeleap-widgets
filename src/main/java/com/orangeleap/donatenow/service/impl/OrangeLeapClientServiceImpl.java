package com.orangeleap.donatenow.service.impl;

import java.util.List;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.WSClient;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.GetConstituentByIdRequest;
import com.orangeleap.client.GetConstituentByIdResponse;
import com.orangeleap.donatenow.service.OrangeLeapClientService;
import org.springframework.stereotype.Service;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orangeleap.donatenow.dao.WidgetDAO;
import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.domain.WidgetData;
import com.orangeleap.donatenow.domain.WidgetExample;

@Service("orangeLeapClientService")
public class OrangeLeapClientServiceImpl implements OrangeLeapClientService {
  private static final Log logger = LogFactory.getLog(OrangeLeapClientServiceImpl.class);

  @Resource(name="constituentCache")
  Cache constituentCache;

  @Autowired
  WidgetDAO widgetDAO = null;


  public void removeFromCache(Long id) {
    constituentCache.remove(id);
  }

  public Constituent getConstituentById(String guid, Long id) {
    Element elem = constituentCache.get(id);
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
      String wsusername = widgets.get(0).getWidgetUsername();
      String wspassword = widgets.get(0).getWidgetPassword();

      WSClient wsClient = null;
      OrangeLeap oleap = null;

      wsClient = new WSClient();
      oleap = wsClient.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),wsusername, wspassword);

      GetConstituentByIdRequest request = new GetConstituentByIdRequest();
      GetConstituentByIdResponse response = null;

      request.setId(id);

      response = oleap.getConstituentById(request);
      if (response != null) {
        Constituent c = response.getConstituent();
        constituentCache.put(new Element(id,c));
        return response.getConstituent();
      }

    }

    return null;
  }
}