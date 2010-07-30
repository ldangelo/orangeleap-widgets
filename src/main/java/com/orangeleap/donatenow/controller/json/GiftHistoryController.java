package com.orangeleap.donatenow.controller.json;

import com.orangeleap.client.GetConstituentGiftRequest;
import com.orangeleap.client.GetConstituentGiftCountRequest;
import com.orangeleap.client.GetConstituentGiftResponse;
import com.orangeleap.client.GetConstituentGiftCountResponse;
import com.orangeleap.client.Gift;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.WSClient;
import com.orangeleap.donatenow.domain.Widget;
import com.orangeleap.donatenow.domain.Placements;
import com.orangeleap.donatenow.domain.WidgetExample;
import com.orangeleap.donatenow.dao.WidgetDAO;
import com.orangeleap.donatenow.service.PlacementsService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
	public void getGiftHistory(@RequestParam(required=true) String guid,@RequestParam(required=true) Long constituentid,ModelMap modelMap) {
      if (guid == null || guid.equals("undefined") || guid.equals("")) {
        return;
      }

      if (constituentid == null || constituentid.equals("undefined") || constituentid.equals("")) {
        return;
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
    oleap = wsClient.getOrangeLeap(System.getProperty("donatenow.wsdllocation"),wsusername, wspassword);

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
