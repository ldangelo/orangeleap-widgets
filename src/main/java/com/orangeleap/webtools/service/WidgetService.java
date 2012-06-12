package com.orangeleap.webtools.service;

import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;

import com.orangeleap.client.CustomTableField;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetData;
import com.orangeleap.webtools.domain.CustomEntity;
import com.orangeleap.client.CustomTable;
import com.orangeleap.client.CustomTableRow;
import com.orangeleap.webtools.domain.WidgetExample;

import javax.servlet.http.HttpServletRequest;

public interface WidgetService {
	public Widget saveWidget(Widget widget);

	public List<Widget> getLoginWidgets(String username, String password);

	public Widget createWidget(String username, String password, String widgettype, String customentitytype, final boolean inactive, final boolean deleted);

	public List<Widget> getWidgets(String username, String password, String widgettype, String customentitytype);

	public Widget getWidget(String guid);

	public List<CustomEntity> getCustomEntity(String guid);

	public CustomTableRow CreateCustomTableRow(String guid, HttpServletRequest request) throws Exception;

	public Widget create();

	public Widget selectWidgetById(Long id);

	public Widget selectWidgetByGuid(String guid);

	List<Widget> selectWidgetByExample(WidgetExample example);

	public List<Widget> listWidgets(String userName, String password);

	public void updateViewCount(String guid, String refererrer);

	public Widget saveOrUpdate(Widget widget);

	public CustomTable getCustomTable(String name);

	public WidgetData process(WidgetData data);

	Map<String, List<Map<String, Object>>> findPicklistItemsForCustomEntities(List<CustomEntity> customEntityList, String guid);

	Map<String, List<Map<String, Object>>> findPicklistItemsForCustomTableFields(List<CustomTableField> customTableFieldsList, String guid);

	public CustomTable getCustomTableByName(String userName, String password,
			String customentitytype);

	public List<CustomTableRow> getCustomTableRows(String guid,	Map<String, String> whereFieldEqualsValue) throws Exception;
}
