package com.orangeleap.webtools.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.orangeleap.webtools.dao.JavascriptDAO;
import com.orangeleap.webtools.domain.Javascript;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("javascriptDAO")
public class JavascriptDAOImpl extends SqlMapClientDaoSupport implements JavascriptDAO {

	@Autowired
	protected JavascriptDAOImpl(SqlMapClient sqlMapClient) {
		setSqlMapClientTemplate(new SqlMapClientTemplate(sqlMapClient));
	}
	
	@Override
	public void insertJavascript(Javascript javascript) {
		getSqlMapClientTemplate().insert("JAVASCRIPT.INSERT_JAVASCRIPT", javascript);
	}

	@Override
	public void updateJavascript(Javascript javascript) {
		getSqlMapClientTemplate().insert("JAVASCRIPT.UPDATE_JAVASCRIPT", javascript);
	}

	@Override
	public Javascript selectJavascriptById(final Long javascriptId) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("javascriptId", javascriptId);
		return (Javascript) getSqlMapClientTemplate().queryForObject("JAVASCRIPT.SELECT_JAVASCRIPT_BY_ID", params);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Javascript> selectJavascriptsByUserName(final String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("userName", userName);
		return getSqlMapClientTemplate().queryForList("JAVASCRIPT.SELECT_JAVASCRIPT_BY_USER_NAME", params);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Javascript> selectJavascriptsBySiteName(final String siteName) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("siteName", siteName);
		return getSqlMapClientTemplate().queryForList("JAVASCRIPT.SELECT_JAVASCRIPT_BY_SITE_NAME", params);
	}
}