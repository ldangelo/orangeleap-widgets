package com.orangeleap.webtools.controller.json;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orangeleap.client.GetConstituentPledgeCountRequest;
import com.orangeleap.client.GetConstituentPledgeCountResponse;
import com.orangeleap.client.GetConstituentPledgeRequest;
import com.orangeleap.client.GetConstituentPledgeResponse;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.PicklistItem;
import com.orangeleap.client.Pledge;
import com.orangeleap.client.WSClient;
import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetExample;
import com.orangeleap.webtools.service.PicklistService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pledges.json")
public class JsonPledge {

	public static final String PROJECT_CODE = "projectCode";
	public static final String PROJECT_CODE_DESCRIPTION = "projectCodeDescription";	
	public static final String MOTIVATION_CODE = "motivationCode";
	public static final String MOTIVATION_CODE_DESCRIPTION = "motivationCodeDescription";	
	public static final String FREQUENCY = "frequency";
	public static final String PLEDGE_STATUS_CANCELLED = "Cancelled";
	public static final String PLEDGE_STATUS_FULFILLED = "Fulfilled";

	@Autowired
	WidgetDAO widgetDAO = null;

	@Autowired
	PicklistService picklistService;

	@Resource(name = "sessionCache")
	Cache sessionCache;

	private Map<String, List<PicklistItem>> loadPicklistItems(final String guid) {
		final Map<String, List<PicklistItem>> picklistItemMap = new HashMap<String, List<PicklistItem>>();

		String wsUserName = null;
		String wsPassword = null;

		WidgetExample example = new WidgetExample();
		example.createCriteria().andWidgetGuidEqualTo(guid);

		List<Widget> widgets = widgetDAO.selectWidgetByExample(example);

		if ( ! widgets.isEmpty() || guid.equals("")) {
		    if (guid.equals("")) {
			    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        wsUserName = auth.getName();
		        wsPassword = (String) auth.getCredentials();
		    }
		    else {
		        final Widget widget = widgets.get(0);
		        wsUserName = widget.getWidgetUsername();
		        wsPassword = widget.getWidgetPassword();
		    }
		}

		List<PicklistItem> projectCodeItems = null;
		List<PicklistItem> motivationCodeItems = null;
		List<PicklistItem> frequencyItems = null;
		try {
			projectCodeItems = picklistService.getPickListItems(wsUserName, wsPassword, PROJECT_CODE,true);
		}
		catch (Exception ex) {

		}
		try {
			motivationCodeItems = picklistService.getPickListItems(wsUserName, wsPassword, MOTIVATION_CODE,true);
		}
		catch (Exception ex) {

		}
		try {
			frequencyItems = picklistService.getPickListItems(wsUserName, wsPassword, FREQUENCY,true);
		}
		catch (Exception ex) {

		}
		picklistItemMap.put(PROJECT_CODE, projectCodeItems);
		picklistItemMap.put(MOTIVATION_CODE, motivationCodeItems);
		picklistItemMap.put(FREQUENCY, frequencyItems);

		return picklistItemMap;
	}

	private void addPledges(final List<Pledge> pledges, final List<Map<String, Object>> returnList, final String guid) {
		final Map<String, List<PicklistItem>> picklistItemMap = loadPicklistItems(guid);

		for (final Pledge pledge : pledges) {
			if ( !pledge.getPledgeType().equals("MONETARY") || pledge.getPledgeStatus().equals(PLEDGE_STATUS_CANCELLED) || pledge.getPledgeStatus().equals(PLEDGE_STATUS_FULFILLED)){
				continue;
			}
			final Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", pledge.getId());
			map.put("donationdate", pledge.getPledgeDate().toString());
			map.put("recurring", pledge.isRecurring());

			if (pledge.isRecurring()) {
				map.put("originalamount", pledge.getAmountPerGift() == null ? 0 : pledge.getAmountPerGift());
				String frequency = getItemDescription(picklistItemMap, pledge.getFrequency(), FREQUENCY);
				map.put("frequency", frequency);
			}
			else {
				map.put("originalamount", pledge.getAmountTotal());
			}
			map.put("amountpaid", pledge.getAmountPaid());
			map.put("amountremaining", pledge.getAmountRemaining());
			map.put("status", pledge.getPledgeStatus());
			map.put("startdate", pledge.getStartDate().toString());
			if (pledge.getEndDate() != null) {
				map.put("enddate", pledge.getEndDate().toString());
			}

			
			if (pledge.getDistributionLines().size() > 0){
				String projectCode = pledge.getDistributionLines().get(0).getProjectCode();
				map.put(PROJECT_CODE, projectCode);
				projectCode = getItemDescription(picklistItemMap, projectCode, PROJECT_CODE);

				map.put(PROJECT_CODE_DESCRIPTION, projectCode);
			
				String motivationCode = pledge.getDistributionLines().get(0).getMotivationCode();
				map.put(MOTIVATION_CODE, motivationCode);
				motivationCode = getItemDescription(picklistItemMap, motivationCode, MOTIVATION_CODE);
				map.put(MOTIVATION_CODE_DESCRIPTION, motivationCode);
			}
			returnList.add(map);
		}
	}

	private String getItemDescription(final Map<String, List<PicklistItem>> codeMap, String itemDescription, final String picklistName) {
		final List<PicklistItem> codeItems = codeMap.get(picklistName);
		if (codeItems != null) {
			for (final PicklistItem anItem : codeItems) {
				if (anItem != null && anItem.getItemName().equalsIgnoreCase(itemDescription)) {
					itemDescription = StringUtils.isNotEmpty(anItem.getLongDescription()) ? anItem.getLongDescription() : anItem.getDefaultDisplayValue();
					break;
				}
			}
		}
		return itemDescription;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void getGiftHistory(@RequestParam(required = true) String guid, @RequestParam(required = true) String sessionId, ModelMap modelMap) {

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
		}
		else {
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
			oleap = wsClient.getOrangeLeap(System.getProperty("webtools.wsdllocation"), wsusername, wspassword);

			GetConstituentPledgeCountRequest countRequest = new GetConstituentPledgeCountRequest();
			GetConstituentPledgeCountResponse countResponse = null;

			countRequest.setConstituentId(constituentid);

			countResponse = oleap.getConstituentPledgeCount(countRequest);

			if (countResponse.getCount() > 0) {
				GetConstituentPledgeRequest request = new GetConstituentPledgeRequest();
				GetConstituentPledgeResponse response = null;

				int offset = 0;
				int limit = 99;

				if (countResponse.getCount() > 100) {
					offset = (int) countResponse.getCount() - 100;
					limit = (int) countResponse.getCount();
				}
				request.setConstituentId(constituentid);
				request.setOffset(offset);
				request.setLimit(limit);

				try {
					response = oleap.getConstituentPledge(request);

					if (response != null) {
						addPledges(response.getPledge(), returnList, guid);
					}
				}
				catch (org.apache.cxf.binding.soap.SoapFault sfe) {

				}

			}
		}
		modelMap.put("rows", returnList);
		modelMap.put("totalRows", returnList.size());
	}
}
