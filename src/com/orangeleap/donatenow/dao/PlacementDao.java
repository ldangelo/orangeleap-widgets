package com.orangeleap.donatenow.dao;


import java.util.List;

import com.orangeleap.donatenow.domain.Placement;

public interface PlacementDao {

    public Placement findPlacementByURL(String url, String site);

    public List<Placement> listPlacementsByWidgetId(Long widgetid,String site);

    public void update(Placement placement);

    public Placement save(Placement placement);

}
