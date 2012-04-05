package com.orangeleap.webtools.service;

import java.util.List;
import com.orangeleap.webtools.domain.Placements;
import com.orangeleap.webtools.domain.PlacementsExample;
import com.orangeleap.webtools.dao.PlacementsDAO;
import com.orangeleap.webtools.domain.Widget;

public interface PlacementsService {
  Placements updateViewCount(Widget widget, String referer);
  Placements updateErrorCount(Widget widget, String referer);
  List<Placements> listPlacementsByWidgetId(Long widgetId);
}

