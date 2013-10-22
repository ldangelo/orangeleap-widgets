package com.orangeleap.webtools.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.orangeleap.webtools.dao.SiteDAO;
import com.orangeleap.webtools.domain.Site;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("siteDAO")
public class SiteDAOImpl extends SqlMapClientDaoSupport implements SiteDAO {
	
	@Autowired
	protected SiteDAOImpl(SqlMapClient sqlMapClient) {
		setSqlMapClientTemplate(new SqlMapClientTemplate(sqlMapClient));
	}
	
	@Override
	public void insertSite(Site site) {
		getSqlMapClientTemplate().insert("SITE.INSERT_SITE", site);
	}

	@Override
	public int updateSite(Site site) {
		return getSqlMapClientTemplate().update("SITE.UPDATE_SITE", site);
	}

	@Override
	public Site getSite(String siteName) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("siteName", siteName);
		return (Site) getSqlMapClientTemplate().queryForObject("SITE.SELECT_BY_SITE_NAME", params);
	}
}