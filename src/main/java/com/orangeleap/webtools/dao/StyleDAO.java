package com.orangeleap.webtools.dao;

import com.orangeleap.webtools.domain.Style;
import java.util.List;

public interface StyleDAO {
  void insertStyle(Style style);
  List<Style> selectStyleById(Style style);
  List<Style> selectStyleByUserName(Style style);
  void updateStyle(Style style);

	List<Style> selectStyleBySiteName(Style record);
}