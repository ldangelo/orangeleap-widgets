package com.orangeleap.donatenow.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.orangeleap.donatenow.dao.DonateWidgetDao;
import com.orangeleap.donatenow.domain.DonateWidget;

public class DonateWidgetDaoImpl extends SqlMapClientDaoSupport implements DonateWidgetDao {

	private String htmlCode;
	
	public String getHtmlCode() {
		
		return htmlCode;
	}

	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}

	@Override
	public DonateWidget findWidgetByGuid(String guid) {
		DonateWidget widget = (DonateWidget) this.getSqlMapClientTemplate().queryForObject("findWidgetByGuid",guid);
		if (widget != null) {
			widget.setWidgetHTML(this.getHtmlCode());
			widget.setWidgetHTML(widget.getWidgetHTML().replaceAll("@GUID@", widget.getGuid()));
			widget.setWidgetHTML(widget.getWidgetHTML().replaceAll("@APPLOCATION@", System.getProperty("donatenow.applocation")));			
		}
		return widget;
	}
	
	@Override
	public DonateWidget findWidgetById(Long id) {
		DonateWidget widget = (DonateWidget) this.getSqlMapClientTemplate().queryForObject("findWidgetById", id);
		if (widget != null) {
			widget.setWidgetHTML(this.getHtmlCode());
			widget.setWidgetHTML(widget.getWidgetHTML().replaceAll("@GUID@", widget.getGuid()));
			widget.setWidgetHTML(widget.getWidgetHTML().replaceAll("@APPLOCATION@", System.getProperty("donatenow.applocation")));						
		}
		return widget;
	}

	@Override
	public DonateWidget insert(DonateWidget widget) {
		Long id = (Long) this.getSqlMapClientTemplate().insert("saveWidget",widget);
		widget.setId(id);
		widget.setWidgetHTML(this.getHtmlCode());
		widget.setWidgetHTML(widget.getWidgetHTML().replaceAll("@GUID@", widget.getGuid()));
			widget.setWidgetHTML(widget.getWidgetHTML().replaceAll("@APPLOCATION@", System.getProperty("donatenow.applocation")));					
		return widget;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DonateWidget> listWidgets(String userName, String password) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userName", userName);
		params.put("passWord", password);
		return (List<DonateWidget>) this.getSqlMapClientTemplate().queryForList("listWidgets",params);
	}
	
	@Override
	public DonateWidget update(DonateWidget widget) {
		this.getSqlMapClientTemplate().update("updateWidget",widget);
		widget.setWidgetHTML(this.getHtmlCode());
		widget.setWidgetHTML(widget.getWidgetHTML().replaceAll("@GUID@", widget.getGuid()));
			widget.setWidgetHTML(widget.getWidgetHTML().replaceAll("@APPLOCATION@", System.getProperty("donatenow.applocation")));					
		return widget;
	}
}
