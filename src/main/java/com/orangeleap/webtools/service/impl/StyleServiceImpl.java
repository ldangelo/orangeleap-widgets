package com.orangeleap.webtools.service.impl;

import java.util.List;

import com.orangeleap.webtools.service.StyleService;
import com.orangeleap.webtools.dao.StyleDAO;
import com.orangeleap.webtools.domain.Style;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("styleService")
public class StyleServiceImpl implements StyleService {
	private static final Log logger = LogFactory.getLog(StyleServiceImpl.class);

	@Autowired
	StyleDAO styleDAO = null;

	public Style insert(Style style) {
		styleDAO.insertStyle(style);
		return style;
	}

	public Style update(Style style) {
		styleDAO.updateStyle(style);
		return style;
	}

	public List<Style> selectByUserName(String userName) {
		Style style = new Style();

		style.setUserName(userName);

		return styleDAO.selectStyleByUserName(style);
	}

	public Style selectById(Long id) {
		Style style = new Style();

		style.setId(id);

		List<Style> list = styleDAO.selectStyleById(style);

		if (list.size() > 0) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
}