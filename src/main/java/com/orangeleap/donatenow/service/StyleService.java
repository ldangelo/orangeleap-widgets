package com.orangeleap.donatenow.service;

import java.util.List;

import com.orangeleap.donatenow.domain.Style;

public interface StyleService {
  public Style insert(Style style);
  public Style update(Style style);
  public Style selectByUserName(String userName);
  public Style selectById(Long Id);
}