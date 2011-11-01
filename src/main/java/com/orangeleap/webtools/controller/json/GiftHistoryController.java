package com.orangeleap.webtools.controller.json;

import com.orangeleap.client.GetConstituentGiftRequest;
import com.orangeleap.client.GetConstituentGiftCountRequest;
import com.orangeleap.client.GetConstituentGiftResponse;
import com.orangeleap.client.GetConstituentGiftCountResponse;
import com.orangeleap.client.Gift;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.WSClient;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.Placements;
import com.orangeleap.webtools.domain.WidgetExample;
import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.service.PlacementsService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/giftHistory.json")
public class GiftHistoryController {
	
	
    @Autowired
    WidgetDAO widgetDAO = null;

    @Resource(name="sessionCache")
    Cache sessionCache;

	private void addGifts(List<Gift> gifts, List<Map<String, Object>> returnList) {
		Iterator<Gift> it = gifts.iterator();

		while (it.hasNext()) {
			Gift gift = it.next();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", gift.getId());
			map.put("donationdate",gift.getDonationDate().toString());
			map.put("amount",gift.getAmount());
			map.put("status",gift.getGiftStatus());
			map.put("paymentstatus", gift.getPaymentStatus());
			returnList.add(map);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public void getGiftHistory(@RequestParam(required=true) String guid,@RequestParam(required=true) String sessionId,ModelMap modelMap) {

		Long constituentid = null;
      if (guid == null || guid.equals("undefined") || guid.equals("")) {
        return;
      }

      if (sessionId == null || sessionId.equals("undefined") || sessionId.equals("")) {
        return;
      }

      //
      // find this sessionId in the cache....
      Element elem = sessionCache.get(sessionId);
      if (elem == null) {
    	  return;
      } else {
    	  constituentid = (Long) elem.getObjectValue();
      }

      List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

    WidgetExample example = new WidgetExample();
    example.createCriteria().andWidgetGuidEqualTo(guid);

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
    oleap = wsClient.getOrangeLeap(System.getProperty("webtools.wsdllocation"),wsusername, wspassword);

    GetConstituentGiftCountRequest giftCountRequest = new GetConstituentGiftCountRequest();
    GetConstituentGiftCountResponse giftCountResponse = null;

    giftCountRequest.setConstituentId(constituentid);

    giftCountResponse = oleap.getConstituentGiftCount(giftCountRequest);

    if (giftCountResponse.getCount() > 0) {
      GetConstituentGiftRequest request = new GetConstituentGiftRequest();
      GetConstituentGiftResponse response = null;

      int offset = 0;
      int limit = 99;

      if (giftCountResponse.getCount() > 100) {
        offset =(int)  giftCountResponse.getCount() - 100;
        limit = (int) giftCountResponse.getCount();
      }
      request.setConstituentId(constituentid);
      request.setOffset(offset);
      request.setLimit(limit);

      try {
        response = oleap.getConstituentGift(request);

        if (response != null) {
          addGifts(response.getGift(),returnList);
        }
      }  catch (org.apache.cxf.binding.soap.SoapFault sfe) {

      }

    }
    }
    modelMap.put("rows", returnList);
    modelMap.put("totalRows", returnList.size());
	}
}
