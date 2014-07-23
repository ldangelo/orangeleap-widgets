package com.orangeleap.webtools.service;

import java.util.List;

import com.orangeleap.webtools.domain.Javascript;

public interface JavascriptService {
  public Javascript insert(Javascript javascript);
  public Javascript update(Javascript javascript);
  public Javascript selectById(Long javascriptId);
  public List<Javascript> selectByUserName(String userName);
  public List<Javascript> selectBySiteName(String siteName);
}