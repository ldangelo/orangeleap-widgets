package com.orangeleap.donatenow.service;

import java.util.List;
import com.orangeleap.donatenow.domain.Placements;
import com.orangeleap.donatenow.domain.PlacementsExample;
import com.orangeleap.donatenow.dao.PlacementsDAO;
import com.orangeleap.donatenow.domain.Widget;

public interface PlacementsService {
  Placements updateViewCount(Widget widget, String referer);
  Placements updateErrorCount(Widget widget, String referer);
  List<Placements> listPlacementsByWidgetId(Long widgetId);
}

