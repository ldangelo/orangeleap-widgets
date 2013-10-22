package com.orangeleap.webtools.service.impl;

import com.orangeleap.webtools.dao.SiteDAO;
import com.orangeleap.webtools.domain.Site;
import com.orangeleap.webtools.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("siteService")
public class SiteServiceImpl implements SiteService {

	@Autowired
	SiteDAO siteDAO = null;

	public Site create() {
		return new Site();
	}

	public Site getSite(String siteName) {
		return siteDAO.getSite(siteName);
	}

	@Override
	public void saveSite(Site site) {
		Site originalSite = getSite(site.getSiteName());
		if (originalSite == null) {
			siteDAO.insertSite(site);
		} else {
			siteDAO.updateSite(site);
		}
	}
}