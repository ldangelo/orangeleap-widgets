package com.orangeleap.webtools.dao;

import com.orangeleap.webtools.domain.Site;

public interface SiteDAO {
    void insertSite(Site site);
    int updateSite(Site site);
    Site getSite(String siteName);
}