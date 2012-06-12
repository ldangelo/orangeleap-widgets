package com.orangeleap.webtools.dao.impl;

import java.util.List;

import com.orangeleap.webtools.dao.StyleDAO;
import com.orangeleap.webtools.domain.Style;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class StyleDAOImpl extends SqlMapClientDaoSupport implements StyleDAO {

	@Override
	public void insertStyle(Style record) {
		getSqlMapClientTemplate().insert("STYLES.insert", record);
	}

	@Override
	public void updateStyle(Style record) {
		getSqlMapClientTemplate().insert("STYLES.update", record);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Style> selectStyleById(final Style record) {
		return getSqlMapClientTemplate().queryForList("STYLES.selectById", record);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Style> selectStyleByUserName(final Style record) {
		return getSqlMapClientTemplate().queryForList("STYLES.selectByUserName", record);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Style> selectStyleBySiteName(final Style record) {
		return getSqlMapClientTemplate().queryForList("STYLES.selectBySiteName", record);
	}
}