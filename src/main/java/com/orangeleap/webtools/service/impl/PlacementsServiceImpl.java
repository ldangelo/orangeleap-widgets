package com.orangeleap.webtools.service.impl;

import java.util.List;
import com.orangeleap.webtools.dao.PlacementsDAO;
import com.orangeleap.webtools.domain.Placements;
import com.orangeleap.webtools.domain.PlacementsExample;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.service.PlacementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service("placementsService")
public class PlacementsServiceImpl implements PlacementsService {
  private static final Log logger = LogFactory.getLog(PlacementsServiceImpl.class);

  @Autowired
  private PlacementsDAO placementsDAO;

  public Placements updateViewCount(Widget widget, String referer) {
    PlacementsExample example = new PlacementsExample();
    example.createCriteria().andWidgetIdEqualTo(widget.getWidgetId()).andPlacementUrlEqualTo(referer);
    List<Placements> placements = placementsDAO.selectPlacementsByExample(example);

    Placements placement = null;
    //
    // this will only return a single placement since placementUrl is unique
    if (placements.size() > 0) {
      placement = placements.get(0);
      placement.setViewCount(placement.getViewCount() + 1);

      placementsDAO.updatePlacementsByPrimaryKey(placement);
    } else {
      placement = new Placements();
      placement.setWidgetId(widget.getWidgetId());
      placement.setErrorCount(0L);
      placement.setViewCount(1L);
      placement.setPlacementUrl(referer);
      placementsDAO.insertPlacements(placement);
    }
    return placement;
}

  public Placements updateErrorCount(Widget widget, String referer) {
    PlacementsExample example = new PlacementsExample();
    example.createCriteria().andWidgetIdEqualTo(widget.getWidgetId()).andPlacementUrlEqualTo(referer);
    List<Placements> placements = placementsDAO.selectPlacementsByExample(example);

    Placements placement = null;
    //
    // this will only return a single placement since placementUrl is unique
    if (placements.size() > 0) {
      placement = placements.get(0);
      placement.setErrorCount(placement.getErrorCount() + 1);
      placement.setViewCount(placement.getViewCount() + 1);

      placementsDAO.updatePlacementsByPrimaryKey(placement);
    } else {
      placement = new Placements();
      placement.setWidgetId(widget.getWidgetId());
      placement.setErrorCount(1L);
      placement.setViewCount(1L);
      placement.setPlacementUrl(referer);
      placementsDAO.insertPlacements(placement);
    }
    return placement;
}

  public List<Placements> listPlacementsByWidgetId(Long widgetid) {
    PlacementsExample example = new PlacementsExample();
    example.createCriteria().andWidgetIdEqualTo(widgetid);
    return placementsDAO.selectPlacementsByExample(example);
  }
}