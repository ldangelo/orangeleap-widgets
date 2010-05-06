package com.orangeleap.donatenow.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.orangeleap.donatenow.dao.PlacementDao;
import com.orangeleap.donatenow.domain.Placement;

public class PlacementDaoImpl extends SqlMapClientDaoSupport implements PlacementDao {

	@Override
	public Placement save(Placement placement) {
		Long id = (Long) this.getSqlMapClientTemplate().insert("savePlacement",placement);
		placement.setId(id);
		
		return placement;
	}
	
	@Override
	public void update(Placement placement) {
		this.getSqlMapClientTemplate().update("updatePlacement",placement);
	}
	
	@Override
	public List<Placement> listPlacementsByWidgetId(Long widgetid) {
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("widgetid",widgetid);

		return (List<Placement>) this.getSqlMapClientTemplate().queryForList("listPlacementsByWidgetId", params);
	}
	
	@Override
	public Placement findPlacementByURL(String url) {
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("url",url);

	    return (Placement) this.getSqlMapClientTemplate().queryForObject("findPlacementByURL",params);	
	}
}
