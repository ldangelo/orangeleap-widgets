package com.orangeleap.donatenow.dao;


import java.util.List;

import com.orangeleap.donatenow.domain.Placement;

public interface PlacementDao {

    public Placement findPlacementByURL(String url);

    public List<Placement> listPlacementsByWidgetId(Long widgetid);

    public void update(Placement placement);

    public Placement save(Placement placement);

}
