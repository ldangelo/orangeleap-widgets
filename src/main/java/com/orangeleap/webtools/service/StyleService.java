package com.orangeleap.webtools.service;

import java.util.List;

import com.orangeleap.webtools.domain.Style;

public interface StyleService {
  public Style insert(Style style);
  public Style update(Style style);
  public List<Style> selectByUserName(String userName);
  public Style selectById(Long Id);
}