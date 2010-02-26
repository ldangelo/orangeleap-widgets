package com.orangeleap.donatenow.dao;

import java.util.List;

import com.orangeleap.donatenow.domain.DonateWidget;

public interface DonateWidgetDao {

	public DonateWidget findWidgetById(Long id);

	public DonateWidget update(DonateWidget widget);

	List<DonateWidget> listWidgets(String userName, String password);

	DonateWidget findWidgetByGuid(String guid);

	DonateWidget insert(DonateWidget widget);
}
