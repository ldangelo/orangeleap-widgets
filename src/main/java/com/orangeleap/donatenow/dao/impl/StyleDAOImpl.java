package com.orangeleap.donatenow.dao.impl;

import com.orangeleap.donatenow.dao.StyleDAO;
import com.orangeleap.donatenow.domain.Style;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class StyleDAOImpl extends SqlMapClientDaoSupport implements StyleDAO {

  public void insertStyle(Style record) {
    getSqlMapClientTemplate().insert("STYLES.insert", record);
  }

  public void updateStyle(Style record) {
    getSqlMapClientTemplate().insert("STYLES.update", record);
  }
  public List<Style> selectStyleById(Style record) {
    List<Style> list = getSqlMapClientTemplate().queryForList("STYLES.selectById",record);
    return list;
 }
  
  public List<Style> selectStyleByUserName(Style record) {
    List<Style> list = getSqlMapClientTemplate().queryForList("STYLES.selectByUserName",record);
    return list;
  }
}