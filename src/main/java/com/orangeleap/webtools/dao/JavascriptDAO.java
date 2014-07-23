package com.orangeleap.webtools.dao;

import com.orangeleap.webtools.domain.Javascript;
import java.util.List;

public interface JavascriptDAO {
	void insertJavascript(Javascript javascript);
	void updateJavascript(Javascript javascript);
	Javascript selectJavascriptById(Long javascriptId);
	List<Javascript> selectJavascriptsByUserName(String userName);
	List<Javascript> selectJavascriptsBySiteName(String siteName);
}