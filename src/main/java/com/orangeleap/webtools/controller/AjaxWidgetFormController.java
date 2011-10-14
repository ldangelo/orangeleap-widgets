package com.orangeleap.webtools.controller;

import org.apache.commons.beanutils.PropertyUtils;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.webtools.domain.CustomEntity;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.service.OrangeLeapClientService;
import com.orangeleap.webtools.service.WidgetService;
import com.orangeleap.webtools.service.StyleService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.orangeleap.webtools.domain.Style;

public class AjaxWidgetFormController extends MultiActionController {
	@Autowired
	WidgetService widgetService;

	@Autowired
	StyleService styleService;
	/**
	 * Describe loginWidgetHTML here.
	 */
	private String loginWidgetHTML;

	/**
	 * Describe donationWidgetHTML here.
	 */
	private String donationWidgetHTML;
	/**
	 * Describe registrationWidgetHTML here.
	 */
	private String registrationWidgetHTML;
	/**
	 * Describe giftHistoryWidgetHTML here.
	 */
	private String giftHistoryWidgetHTML;
	/**
	 * Describe sponsorableWidgetHTML here.
	 */
	private String sponsorableWidgetHTML;
	/**
	 * Describe sponsorshipWidgetHTML here.
	 */
	private String sponsorshipWidgetHTML;

    private String pledgesWidgetHTML;


    /**
	 * Describe iframeHTML here.
	 */
	private String iframeHTML;

    public String getRecurringGiftWidgetHTML() {
        return recurringGiftWidgetHTML;
    }

    public void setRecurringGiftWidgetHTML(String recurringGiftWidgetHTML) {
        this.recurringGiftWidgetHTML = recurringGiftWidgetHTML;
    }

    private String recurringGiftWidgetHTML;

	/**
	 * Get the <code>IframeHTML</code> value.
	 * 
	 * @return a <code>String</code> value
	 */
	public final String getIframeHTML() {
		return iframeHTML;
	}

    public String getPledgesWidgetHTML() {
        return pledgesWidgetHTML;
    }

    public void setPledgesWidgetHTML(String pledgesWidgetHTML) {
        this.pledgesWidgetHTML = pledgesWidgetHTML;
    }

	/**
	 * Set the <code>IframeHTML</code> value.
	 * 
	 * @param iframeHTML
	 *            The new IframeHTML value.
	 */
	public final void setIframeHTML(final String iframeHTML) {
		this.iframeHTML = iframeHTML;
	}

	/**
	 * Get the <code>SponsorshipWidgetHTML</code> value.
	 * 
	 * @return a <code>String</code> value
	 */
	public final String getSponsorshipWidgetHTML() {
		return sponsorshipWidgetHTML;
	}

	/**
	 * Set the <code>SponsorshipWidgetHTML</code> value.
	 * 
	 * @param sponsorshipWidgetHTML
	 *            The new SponsorshipWidgetHTML value.
	 */
	public final void setSponsorshipWidgetHTML(
			final String sponsorshipWidgetHTML) {
		this.sponsorshipWidgetHTML = sponsorshipWidgetHTML;
	}

	/**
	 * Get the <code>SponsorableWidgetHTML</code> value.
	 * 
	 * @return a <code>String</code> value
	 */
	public final String getSponsorableWidgetHTML() {
		return sponsorableWidgetHTML;
	}

	/**
	 * Set the <code>SponsorableWidgetHTML</code> value.
	 * 
	 * @param sponsorableWidgetHTML
	 *            The new SponsorableWidgetHTML value.
	 */
	public final void setSponsorableWidgetHTML(
			final String sponsorableWidgetHTML) {
		this.sponsorableWidgetHTML = sponsorableWidgetHTML;
	}

	/**
	 * Get the <code>GiftHistoryWidgetHTML</code> value.
	 * 
	 * @return a <code>String</code> value
	 */
	public final String getGiftHistoryWidgetHTML() {
		return giftHistoryWidgetHTML;
	}

	/**
	 * Set the <code>GiftHistoryWidgetHTML</code> value.
	 * 
	 * @param giftHistoryWidgetHTML
	 *            The new GiftHistoryWidgetHTML value.
	 */
	public final void setGiftHistoryWidgetHTML(
			final String giftHistoryWidgetHTML) {
		this.giftHistoryWidgetHTML = giftHistoryWidgetHTML;
	}

	/**
	 * Get the <code>RegistrationWidgetHTML</code> value.
	 * 
	 * @return a <code>String</code> value
	 */
	public final String getRegistrationWidgetHTML() {
		return registrationWidgetHTML;
	}

	/**
	 * Set the <code>RegistrationWidgetHTML</code> value.
	 * 
	 * @param registrationWidgetHTML
	 *            The new RegistrationWidgetHTML value.
	 */
	public final void setRegistrationWidgetHTML(
			final String registrationWidgetHTML) {
		this.registrationWidgetHTML = registrationWidgetHTML;
	}

	/**
	 * Get the <code>DonationWidgetHTML</code> value.
	 * 
	 * @return a <code>String</code> value
	 */
	public final String getDonationWidgetHTML() {
		return donationWidgetHTML;
	}

	/**
	 * Set the <code>DonationWidgetHTML</code> value.
	 * 
	 * @param donationWidgetHTML
	 *            The new DonationWidgetHTML value.
	 */
	public final void setDonationWidgetHTML(final String donationWidgetHTML) {
		this.donationWidgetHTML = donationWidgetHTML;
	}

	/**
	 * Get the <code>LoginWidgetHTML</code> value.
	 * 
	 * @return a <code>String</code> value
	 */
	public final String getLoginWidgetHTML() {
		return loginWidgetHTML;
	}

	/**
	 * Set the <code>LoginWidgetHTML</code> value.
	 * 
	 * @param loginWidgetHTML
	 *            The new LoginWidgetHTML value.
	 */
	public final void setLoginWidgetHTML(final String loginWidgetHTML) {
		this.loginWidgetHTML = loginWidgetHTML;
	}

	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getModelMapError("Unimplemented");
	}

	public ModelAndView read(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String guid = request.getParameter("guid");
		String userName = auth.getName();
		String password = (String) auth.getCredentials();

		Widget w = widgetService.selectWidgetByGuid(guid);

		return getModelMap(w, w.getWidgetType(), w.getCustomEntityName());
	}

	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String widgettype = request.getParameter("widgettype");
		String customentitytype = request.getParameter("customentitytype");
		String userName = auth.getName();
		String password = (String) auth.getCredentials();

		Widget ret = widgetService.createWidget(userName, password, widgettype,
				customentitytype);
		ret.setWidgetId(0L);
		ret.setWidgetHtml("Undefined");
		return getModelMap(ret, widgettype, customentitytype);
	}

	private void populateWidget(Widget widget, HttpServletRequest request) {
		Map paramaterMap = request.getParameterMap();
		Set keySet = paramaterMap.keySet();
		Iterator it = keySet.iterator();
		HashMap map = new HashMap();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = request.getParameter(key);
			try {
				BeanUtils.setProperty(widget, key, value);
			} catch (Exception e) {
				int i = 0;
			}
		}
	}

	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String widgettype = request.getParameter("widgettype");
		String customentitytype = request.getParameter("customentitytype");
		String userName = auth.getName();
		String password = (String) auth.getCredentials();
		String appLocation = System.getProperty("webtools.applocation");
		Widget widget = widgetService.createWidget(userName, password,
				widgettype, customentitytype);
		Style style = null;

		populateWidget(widget, request);

		widget.setWidgetId(0L);
		widget.setWidgetGuid(UUID.randomUUID().toString());
		widget.setWidgetLoginSuccessURL(request
				.getParameter("widgetLoginSuccessURL"));

		if (customentitytype.equals("widget_authentication")) {
			widget.setWidgetHtml(loginWidgetHTML);
		} else if (customentitytype.equals("donor_profile")) {
			widget.setWidgetHtml(registrationWidgetHTML);
		} else if (customentitytype.equals("sponsorable")) {
			widget.setWidgetHtml(sponsorableWidgetHTML);
		} else if (customentitytype.equals("online_sponsorship")) {
			widget.setWidgetHtml(sponsorshipWidgetHTML);
		} else if (customentitytype.equals("online_donation")) {
			widget.setWidgetHtml(donationWidgetHTML);
		} else if (customentitytype.equals("online_recurringgift")) {
            widget.setWidgetHtml(recurringGiftWidgetHTML);
        } else if (customentitytype.equals("online_registration")) {
			widget.setWidgetHtml(registrationWidgetHTML);
		} else if (customentitytype.equals("undefined")
				&& widgettype.equals("gifthistory")) {
			widget.setWidgetHtml(giftHistoryWidgetHTML);
		} else if (customentitytype.equals("undefined") && widgettype.equals("pledges")) {
            widget.setWidgetHtml(pledgesWidgetHTML);
        }

		widget.setIframeHtml(this.getIframeHTML()
				.replaceAll("@APPLOCATION@", appLocation)
				.replaceAll("@GUID@", widget.getWidgetGuid()));
		widget.setWidgetCreateDate(new Date());
		widget.setWidgetErrorCount(0L);
		widget.setWidgetViewCount(0L);
		widgetService.saveWidget(widget);

		return getModelMap(widget, widgettype, customentitytype);
	}

	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getModelMapError("Unimplemented");
	}

	private ModelAndView getModelMap(List<Widget> widgets, String widgettype,
			String customentitytype) {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String, Object> metaData = new HashMap<String, Object>();

		metaData.put("idProperty", "id");
		metaData.put("root", "rows");
		metaData.put("totalProperty", "totalRows");
		metaData.put("successProperty", "success");

		List<Map<String, Object>> fields = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		map = new HashMap<String, Object>();
		map.put("name", "widgetGuid");
		map.put("readonly", true);
		map.put("required", false);
		map.put("type", "text");
		map.put("header", "Widget GUID");
		fields.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "widgetAuthenticationRequired");
		map.put("readonly", false);
		map.put("required", true);
		map.put("type", "boolean");
		map.put("header", "Authentication Required");
		fields.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "widgetAuthenticationURL");
		map.put("readonly", false);
		map.put("required", false);
		map.put("type", "text");
		map.put("header", "Authentication URL");
		fields.add(map);


			map = new HashMap<String, Object>();
			map.put("name", "widgetLoginSuccessURL");
			map.put("readonly", false);
			map.put("required", true);
			map.put("type", "text");
			map.put("header", "Widget Success URL");
			fields.add(map);

		if (customentitytype.equals("online_donation") || customentitytype.equals("online_recurringgift")) {
			map = new HashMap<String, Object>();
			map.put("name", "projectCode");
			map.put("readonly", false);
			map.put("required", true);
			map.put("type", "picklist");
			map.put("header", "Project Code");
			map.put("pickListId", "projectCode");
			fields.add(map);

            map = new HashMap<String,Object>();
            map.put("name","motivationCode");
            map.put("readonly",false);
            map.put("required",false);
            map.put("type","picklist");
            map.put("header","Motivation Code");
            map.put("picklistId","motivationCode");
            fields.add(map);
		} else if (customentitytype.equals("sponsorable")) {
			map = new HashMap<String, Object>();
			map.put("name", "sponsorshipURL");
			map.put("readonly", false);
			map.put("required", true);
			map.put("type", "text");
			map.put("header", "Sponsorship URL");
			map.put("pickListId", "");
			fields.add(map);
		} else if (widgettype.equals("pledges")) {
            map = new HashMap<String, Object>();
			map.put("name", "donationUrl");
			map.put("readonly", false);
			map.put("required", true);
			map.put("type", "text");
			map.put("header", "Donation URL");
			map.put("pickListId", "");
			fields.add(map);
        }

		map = new HashMap<String, Object>();
		map.put("name", "iframeHtml");
		map.put("readonly", true);
		map.put("required", false);
		map.put("type", "comment");
		map.put("header", "Widget HTML");
		fields.add(map);

		metaData.put("fields", fields);

		modelMap.put("metaData", metaData);
		modelMap.put("rows", widgets);
		modelMap.put("success", true);
		modelMap.put("totalRows", widgets.size());
		return new ModelAndView("jsonView", modelMap);
	}

	private ModelAndView getModelMap(Widget widget, String widgettype,
			String customentitytype) {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String, Object> metaData = new HashMap<String, Object>();

		metaData.put("idProperty", "widgetId");
		metaData.put("root", "rows");
		metaData.put("totalProperty", "totalRows");
		metaData.put("successProperty", "success");

		List<Map<String, Object>> fields = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		map = new HashMap<String, Object>();
		map.put("name", "widgetGuid");
		map.put("readonly", true);
		map.put("required", false);
		map.put("type", "text");
		map.put("header", "Widget GUID");
		fields.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "widgetAuthenticationRequired");
		map.put("readonly", false);
		map.put("required", true);
		map.put("type", "boolean");
		map.put("header", "Authentication Required");
		fields.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "widgetAuthenticationURL");
		map.put("readonly", false);
		map.put("required", false);
		map.put("type", "text");
		map.put("header", "Authentication URL");
		fields.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "styleId");
		map.put("readonly", false);
		map.put("required", true);
		map.put("type", "style");
		map.put("header", "Style");
		fields.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "widgetLoginSuccessURL");
		map.put("readonly", false);
		
		if (customentitytype.equals("widget_authentication"))
			map.put("required", true);
		else
			map.put("required", false);
		map.put("type", "text");
		map.put("header", "Widget Success URL");

		fields.add(map);

		if (customentitytype.equals("online_donation") || customentitytype.equals("online_recurringgift")) {
			map = new HashMap<String, Object>();
			map.put("name", "projectCode");
			map.put("readonly", false);
			map.put("required", true);
			map.put("type", "picklist");
			map.put("header", "Project Code");
			fields.add(map);

            map = new HashMap<String,Object>();
            map.put("name","motivationCode");
            map.put("readonly",false);
            map.put("required",false);
            map.put("type","picklist");
            map.put("header","Motivation Code");
            map.put("picklistId","motivationCode");
            fields.add(map);
		} else if (customentitytype.equals("sponsorable")) {
			map = new HashMap<String, Object>();
			map.put("name", "sponsorshipURL");
			map.put("readonly", false);
			map.put("required", true);
			map.put("type", "text");
			map.put("header", "Sponsorship URL");
			map.put("pickListId", "");
			fields.add(map);
		}  else if (widgettype.equals("pledges")) {
            map = new HashMap<String, Object>();
			map.put("name", "donationUrl");
			map.put("readonly", false);
			map.put("required", true);
			map.put("type", "text");
			map.put("header", "Donation URL");
			map.put("pickListId", "");
			fields.add(map);
        }

		map = new HashMap<String, Object>();
		map.put("name", "iframeHtml");
		map.put("readonly", true);
		map.put("required", false);
		map.put("type", "comment");
		map.put("header", "Widget HTML");
		fields.add(map);

		metaData.put("fields", fields);

		modelMap.put("metaData", metaData);
		modelMap.put("rows", widget);
		modelMap.put("success", true);
		modelMap.put("totalRows", 1);
		return new ModelAndView("jsonView", modelMap);
	}

	private ModelAndView getModelMapError(String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return new ModelAndView("jsonView", modelMap);
	}

}