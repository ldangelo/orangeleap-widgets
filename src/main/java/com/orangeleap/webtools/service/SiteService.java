package com.orangeleap.webtools.service;

import com.orangeleap.webtools.domain.Site;

public interface SiteService {
	public void saveSite(Site site);

	public Site getSite(String siteName);
}
