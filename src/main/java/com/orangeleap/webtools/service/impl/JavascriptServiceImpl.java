package com.orangeleap.webtools.service.impl;

import java.util.List;

import com.orangeleap.webtools.service.JavascriptService;
import com.orangeleap.webtools.dao.JavascriptDAO;
import com.orangeleap.webtools.domain.Javascript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("javascriptService")
public class JavascriptServiceImpl implements JavascriptService {

	@Autowired
	JavascriptDAO javascriptDAO;

	@Override
	public Javascript insert(Javascript javascript) {
		javascriptDAO.insertJavascript(javascript);
		return javascript;
	}

	@Override
	public Javascript update(Javascript javascript) {
		javascriptDAO.updateJavascript(javascript);
		return javascript;
	}

	@Override
	public Javascript selectById(Long javascriptId) {
		return javascriptDAO.selectJavascriptById(javascriptId);
	}
	
	@Override
	public List<Javascript> selectByUserName(final String userName) {
		return javascriptDAO.selectJavascriptsByUserName(userName);
	}

	@Override
	public List<Javascript> selectBySiteName(final String siteName) {
		return javascriptDAO.selectJavascriptsBySiteName(siteName);
	}
}