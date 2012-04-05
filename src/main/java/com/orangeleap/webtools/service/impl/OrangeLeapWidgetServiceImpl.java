package com.orangeleap.webtools.service.impl;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.jasperserver.irplugin.JServer;
import com.jaspersoft.jasperserver.irplugin.RepositoryReportUnit;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry;
import com.orangeleap.client.Constituent;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.Filter;
import com.orangeleap.client.GetConstituentByIdRequest;
import com.orangeleap.client.GetConstituentByIdResponse;
import com.orangeleap.client.GetCustomTableRowsRequest;
import com.orangeleap.client.GetCustomTableRowsResponse;
import com.orangeleap.client.Gift;
import com.orangeleap.client.OrangeLeap;
import com.orangeleap.client.SaveOrUpdateCustomTableRowRequest;
import com.orangeleap.client.SaveOrUpdateCustomTableRowResponse;
import com.orangeleap.client.Site;
import com.orangeleap.client.WSClient;
import com.orangeleap.common.security.CasUtil;
import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Session;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetData;
import com.orangeleap.webtools.domain.WidgetExample;
import com.orangeleap.webtools.service.OrangeLeapClientService;
import com.orangeleap.webtools.service.OrangeLeapWidgetService;
import com.orangeleap.webtools.service.PlacementsService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;

public class OrangeLeapWidgetServiceImpl implements OrangeLeapWidgetService {
	private static final Log logger = LogFactory
			.getLog(OrangeLeapWidgetServiceImpl.class);
	private String baseUri = null;
	private String repositoryUri = null;

	@Autowired
	WidgetDAO widgetDAO = null;

	@Autowired
	PlacementsService placementsService;

	@Autowired
	OrangeLeapClientService orangeLeapClientService;

	@Resource(name = "sessionCache")
	Cache sessionCache;

	private Boolean setCustomFieldMapValue(CustomFieldMap map, String fieldName, String value) {
		List<Entry> entries = map.getEntry();
		Iterator<Entry> itEntries = entries.iterator();
		while (itEntries.hasNext()) {
			Entry entry = itEntries.next();

			if (entry.getKey().equals(fieldName)) {
				entry.getValue().setValue(value);
				return true;
			}
		}
		return false;
	}

	private String customFieldMapValue(CustomFieldMap map, String fieldName) {
		List<Entry> entries = map.getEntry();
		Iterator<Entry> itEntries = entries.iterator();
		while (itEntries.hasNext()) {
			Entry entry = itEntries.next();

			if (entry.getKey().equals(fieldName)) {
				return entry.getValue().getValue();
			}
		}
		return null;
	}

	public Session authenticate(String guid, String username, String password) {
		WidgetExample example = new WidgetExample();
		List<Widget> widgets = null;

		example.createCriteria().andWidgetGuidEqualTo(guid);
		widgets = widgetDAO.selectWidgetByExample(example);

		if (widgets == null) {
			return null;
		}

		String wsusername = widgets.get(0).getWidgetUsername();
		String wspassword = widgets.get(0).getWidgetPassword();

		WSClient wsClient = null;
		OrangeLeap oleap = null;

		wsClient = new WSClient();
		oleap = wsClient.getOrangeLeap(
				System.getProperty("webtools.wsdllocation"), wsusername,
				wspassword);

		GetCustomTableRowsRequest webwidgetRequest = new GetCustomTableRowsRequest();
		GetCustomTableRowsResponse webwidgetResponse = null;

		Filter filter = new Filter();
		filter.setName("user_name");
		filter.setValue(username);

		webwidgetRequest.setTablename("online_registration");
		webwidgetRequest.setOffset(0);
		webwidgetRequest.setLimit(1);
		webwidgetRequest.getFilters().add(filter);

		try {
			webwidgetResponse = oleap.getCustomTableRows(webwidgetRequest);
		}
		catch (Exception e) {
			logger.error("Errror: " + e.getMessage());
			return null;
		}

		if (webwidgetResponse != null) {
			if (webwidgetResponse.getCustomTableRow().size() > 0) {
				CustomTableRow row = webwidgetResponse.getCustomTableRow().get(
						0);
				String val = customFieldMapValue(row.getCustomFieldMap(),
						"user_password");
				if (val != null && val.equals(password)) {
					Session s = new Session();
					s.setConstituentId(Long.parseLong(customFieldMapValue(
							row.getCustomFieldMap(), "constituent_id")));
					s.setSessionId(UUID.randomUUID().toString());
					sessionCache.put(new Element(s.getSessionId(), s.getConstituentId()));
					return s;
				}
			}
		}

		return null;
	}

	public WidgetData create() {
		return new WidgetData();
	}

	public Widget selectWidgetById(Long id) {
		return widgetDAO.selectWidgetByPrimaryKey(id);
	}

	public Constituent getConstituent(String guid, String sessionId) {
		Element elem = sessionCache.get(sessionId);
		Long id = (Long) elem.getObjectValue();
		return orangeLeapClientService.getConstituentById(guid, id);
	}

	public List<Gift> getConstituentGifts(String guid, String sessionId) {
		Element elem = sessionCache.get(sessionId);
		Long id = (Long) elem.getObjectValue();
		return orangeLeapClientService.getConstituentGifts(guid, id);
	}

	public WidgetData process(WidgetData data) {
		return data;
	}

	public List<Widget> listWidgets(String userName, String password) {
		WidgetExample example = new WidgetExample();

		example.createCriteria().andWidgetUsernameEqualTo(userName);
		example.createCriteria().andWidgetPasswordEqualTo(password);

		return widgetDAO.selectWidgetByExample(example);
	}

	public void updateViewCount(String guid, String refererrer) {
		WidgetExample example = new WidgetExample();
		example.createCriteria().andWidgetGuidEqualTo(guid);
		List<Widget> widgets = widgetDAO.selectWidgetByExample(example);

		//
		// guid is a unique key so this will only return one widget
		Widget widget = widgets.get(0);

		widget.setWidgetViewCount(widget.getWidgetViewCount() + 1);
		widgetDAO.updateWidgetByPrimaryKey(widget);

		placementsService.updateViewCount(widget, refererrer);
	}

	public void updateErrorCount(String guid, String refererrer) {
		WidgetExample example = new WidgetExample();
		example.createCriteria().andWidgetGuidEqualTo(guid);
		List<Widget> widgets = widgetDAO.selectWidgetByExample(example);

		//
		// guid is a unique key so this will only return one widget
		Widget widget = widgets.get(0);

		widget.setWidgetErrorCount(widget.getWidgetErrorCount() + 1);
		widgetDAO.updateWidgetByPrimaryKey(widget);

		placementsService.updateErrorCount(widget, refererrer);
	}

	public String getRoles() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (auth == null) {
			return "";
		}

		String strAuthorities = "";
		GrantedAuthority[] authorities = auth.getAuthorities();

		for (int i = 0; i < authorities.length; i++) {
			strAuthorities += authorities[i].getAuthority() + ',';
		}
		return strAuthorities;
	}


	public Widget saveOrUpdate(Widget widget) {
		if (widget.getWidgetCreateDate() == null) {
			widget.setWidgetCreateDate(new Date());
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();


		String userName = auth.getName();
		String password = (String) auth.getCredentials();
		String siteName = userName.substring(userName.indexOf('@') + 1);
		widget.setWidgetUsername(userName);
		widget.setWidgetPassword(password);

		Widget result = null;

		if (widget.getWidgetId() != null) {
			widgetDAO.updateWidgetByPrimaryKey(widget);
		}
		else {
			widgetDAO.insertWidget(widget);
		}

		return widget;
	}

	public CustomTableRow addCustomTableRow(String guid, Entry[] row) {
		logger.error(row.toString());
		return null;
	}

	@Override
	public String changePassword(String guid, String username,
			String oldpassword, String newpassword) {
		WidgetExample example = new WidgetExample();
		List<Widget> widgets = null;
		String returnstr = "";

		example.createCriteria().andWidgetGuidEqualTo(guid);
		widgets = widgetDAO.selectWidgetByExample(example);

		if (widgets == null) {
			return null;
		}

		String wsusername = widgets.get(0).getWidgetUsername();
		String wspassword = widgets.get(0).getWidgetPassword();

		WSClient wsClient = null;
		OrangeLeap oleap = null;

		wsClient = new WSClient();
		oleap = wsClient.getOrangeLeap(
				System.getProperty("webtools.wsdllocation"), wsusername,
				wspassword);

		GetCustomTableRowsRequest webwidgetRequest = new GetCustomTableRowsRequest();
		GetCustomTableRowsResponse webwidgetResponse = null;

		Filter filter = new Filter();
		filter.setName("user_name");
		filter.setValue(username);

		webwidgetRequest.setTablename("online_registration");
		webwidgetRequest.setOffset(0);
		webwidgetRequest.setLimit(1);
		webwidgetRequest.getFilters().add(filter);

		try {
			webwidgetResponse = oleap.getCustomTableRows(webwidgetRequest);
		}
		catch (Exception e) {
			logger.error("Errror: " + e.getMessage());
			return null;
		}

		if (webwidgetResponse != null) {
			if (webwidgetResponse.getCustomTableRow().size() > 0) {
				CustomTableRow row = webwidgetResponse.getCustomTableRow().get(
						0);
				String val = customFieldMapValue(row.getCustomFieldMap(),
						"user_password");
				if (val != null && val.equals(oldpassword)) {
					//
					// reset the password
					SaveOrUpdateCustomTableRowRequest updaterequest = new SaveOrUpdateCustomTableRowRequest();
					SaveOrUpdateCustomTableRowResponse updateresponse = new SaveOrUpdateCustomTableRowResponse();

					if (setCustomFieldMapValue(row.getCustomFieldMap(), "user_password", newpassword) &&
							setCustomFieldMapValue(row.getCustomFieldMap(),"confirm_password",newpassword)) {
						updaterequest.setCustomTableRow(row);
						updateresponse = oleap.saveOrUpdateCustomTableRow(updaterequest);

						if (updateresponse != null) {
							returnstr = "Your password has been successfully changed";
						}
					}
					else {
						returnstr = "Unable to update password";
					}

				}
				else {
					returnstr = "Invalid Old Password";
				}
			}
			else {
				returnstr = "Unable to locate user.";
			}
		}
		else {
			returnstr = "Error retrieving user information from Orange Leap";
		}

		return returnstr;
	}

	@Override
	public String forgotPassword(String guid, String username) {
		// TODO Auto-generated method stub
		WidgetExample example = new WidgetExample();
		List<Widget> widgets = null;
		String returnstr = "";

		example.createCriteria().andWidgetGuidEqualTo(guid);
		widgets = widgetDAO.selectWidgetByExample(example);

		if (widgets == null) {
			return null;
		}

		String wsusername = widgets.get(0).getWidgetUsername();
		String wspassword = widgets.get(0).getWidgetPassword();

		WSClient wsClient = null;
		OrangeLeap oleap = null;

		wsClient = new WSClient();
		oleap = wsClient.getOrangeLeap(
				System.getProperty("webtools.wsdllocation"), wsusername,
				wspassword);

		GetCustomTableRowsRequest webwidgetRequest = new GetCustomTableRowsRequest();
		GetCustomTableRowsResponse webwidgetResponse = null;

		Filter filter = new Filter();
		filter.setName("user_name");
		filter.setValue(username);

		webwidgetRequest.setTablename("online_authentication");
		webwidgetRequest.setOffset(0);
		webwidgetRequest.setLimit(1);
		webwidgetRequest.getFilters().add(filter);

		try {
			webwidgetResponse = oleap.getCustomTableRows(webwidgetRequest);
		}
		catch (Exception e) {
			logger.error("Error: " + e.toString());
			return null;
		}

		if (webwidgetResponse != null) {
			if (webwidgetResponse.getCustomTableRow().size() > 0) {
				CustomTableRow row = webwidgetResponse.getCustomTableRow().get(
						0);
				String val = customFieldMapValue(row.getCustomFieldMap(),
						"user_password");
				if (val != null) {
					//
					// email the password
					Long id = Long.parseLong(customFieldMapValue(
							row.getCustomFieldMap(), "constituent_id"));
					GetConstituentByIdRequest getRequest = new GetConstituentByIdRequest();
					GetConstituentByIdResponse getResponse = new GetConstituentByIdResponse();
					getRequest.setId(id);
					getResponse = oleap.getConstituentById(getRequest);
					if (getResponse != null) {
						Constituent c = getResponse.getConstituent();
						String emailAddr = c.getPrimaryEmail().getEmailAddress();
						Site site = c.getSite();

						//
						// send the email
						JavaMailSenderImpl sender = new JavaMailSenderImpl();
						sender.setHost(site.getSmtpServerName());
						sender.setUsername(site.getSmtpAccountName());
						sender.setPassword(site.getSmtpPassword());

						MimeMessage message = sender.createMimeMessage();
						MimeMessageHelper helper = new MimeMessageHelper(message);

						try {
							helper.setFrom(site.getSmtpFromAddress());
							helper.setTo(emailAddr);
							helper.setSubject("Your Password");
							helper.setText("Your password is " + val);

							sender.send(message);
							returnstr = "An email was sent to the password for the account";
						}
						catch (Exception e) {

							returnstr = "Failed to send e-mail due to an error";
						}

					}
					else {
						returnstr = "Unable to locate constituent record";
					}
				}
			}
			else {
				returnstr = "Unable to locate user";
			}
		}
		else {
			returnstr = "Error retrieving user information from Orange Leap";
		}

		return returnstr;
	}

	private RepositoryReportUnit getReportUnit(JServer jserver, String templatename, Map map, Site s) {
		ResourceDescriptor rd = new ResourceDescriptor();

		rd.setName(templatename);
		rd.setParentFolder("/Reports/" + s.getName()
				+ "/emailTemplates");
		rd.setUriString(rd.getParentFolder() + "/" + rd.getName());
		rd.setWsType(ResourceDescriptor.TYPE_REPORTUNIT);
		List<ResourceProperty> p = new ArrayList<ResourceProperty>();

		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> me = it.next();

			ResourceProperty rp = new ResourceProperty(me.getKey().toString(),
					me.getValue().toString());
			p.add(rp);
		}

		rd.setParameters(p);

		return new RepositoryReportUnit(jserver, rd);
	}

	private File runReport(Site site, Map params, String templateName) {

		File temp = null;
		JServer jserver = new JServer();
		jserver.setUsername(site.getJasperUserId());
		jserver.setPassword(site.getJasperPassword());
		jserver.setUrl(baseUri + repositoryUri);

		try {

			CasUtil.populateJserverWithCasCredentials(jserver, baseUri
					+ "/j_acegi_cas_security_check");


			JasperPrint print = jserver.getWSClient().runReport(
					getReportUnit(jserver, templateName, params, site).getDescriptor(), params);

			temp = File.createTempFile("orangeleap", ".pdf");
			temp.deleteOnExit();
			OutputStream out = new FileOutputStream(temp);
			JasperExportManager.exportReportToPdfStream(print, out);
			out.close();

		}
		catch (JRException e) {

			logger.error(e.getMessage());
			return null;
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return temp;
	}

	public String sendGiftReceipt(Long giftId, String sessionId, String guid) {
		String returnStr = "";
		Constituent c = null;

		Element elem = null;

		elem = sessionCache.get(sessionId);

		if (elem == null) {
			returnStr = "Unable to locate session id";
			return returnStr;
		}

		Long constituentId = (Long) elem.getObjectValue();

		if (constituentId == null) {
			returnStr = "Unable to locate constituent Id";
			return returnStr;
		}
		WidgetExample example = new WidgetExample();
		List<Widget> widgets = null;
		String returnstr = "";

		example.createCriteria().andWidgetGuidEqualTo(guid);
		widgets = widgetDAO.selectWidgetByExample(example);

		if (widgets == null) {
			return null;
		}

		String wsusername = widgets.get(0).getWidgetUsername();
		String wspassword = widgets.get(0).getWidgetPassword();

		WSClient wsClient = null;
		OrangeLeap oleap = null;

		wsClient = new WSClient();
		oleap = wsClient.getOrangeLeap(
				System.getProperty("webtools.wsdllocation"), wsusername,
				wspassword);
		GetConstituentByIdRequest getRequest = new GetConstituentByIdRequest();
		GetConstituentByIdResponse getResponse = new GetConstituentByIdResponse();
		getRequest.setId(constituentId);
		try {
			getResponse = oleap.getConstituentById(getRequest);
		}
		catch (Exception e) {
			returnStr = e.getMessage();
			return returnStr;
		}

		if (getResponse != null) {
			c = getResponse.getConstituent();
			String emailAddr = c.getPrimaryEmail().getEmailAddress();
			Site site = c.getSite();

			//
			// send the email
			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			sender.setHost(site.getSmtpServerName());
			sender.setUsername(site.getSmtpAccountName());
			sender.setPassword(site.getSmtpPassword());

			Map params = new HashMap();
			params.put("Id", c.getId().toString());
			params.put("GiftId", giftId.toString());
			params.put("GiftAmount", "10.00");

			File f = runReport(c.getSite(), params, "DonorThankYouEmail");

			if (f != null) {
				FileSystemResource file = new FileSystemResource(f);
				try {
					MimeMessage message = sender.createMimeMessage();
					MimeMessageHelper helper;
					try {
						helper = new MimeMessageHelper(message, true, "UTF-8");

						helper.addAttachment("DonorThankYouEmail" + ".pdf", file);


						helper.setText("You receipt is attached to this message.");
						helper.setSubject("Your Receipt");
						helper.setFrom(site.getSmtpFromAddress());

						helper.addTo(c.getPrimaryEmail().getEmailAddress());


						//
						// finally we mail the message
						sender.send(message);
						f.delete();


					}
					catch (MessagingException e1) {
						logger.error(e1.getMessage());
						return e1.getMessage();
					}
				}
				catch (Exception e) {

					logger.error(e.getMessage());
					return e.getMessage();
				}
			}
		}
		else {
			return "Unable to find constituent.";
		}
		return "Your receipt has been e-mailed to " + c.getPrimaryEmail().getEmailAddress();
	}

	public String getBaseUri
			() {
		return baseUri;
	}

	public void setBaseUri
			(String
					baseUri) {
		this.baseUri = baseUri;
	}

	public String getRepositoryUri
			() {
		return repositoryUri;
	}

	public void setRepositoryUri
			(String
					repositoryUri) {
		this.repositoryUri = repositoryUri;
	}
}