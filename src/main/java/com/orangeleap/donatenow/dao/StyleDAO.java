package com.orangeleap.donatenow.dao;

import com.orangeleap.donatenow.domain.Style;
import java.util.List;

public interface StyleDAO {
  void insertStyle(Style style);
  List<Style> selectStyleById(Style style);
  List<Style> selectStyleByUserName(Style style);
  void updateStyle(Style style);
}