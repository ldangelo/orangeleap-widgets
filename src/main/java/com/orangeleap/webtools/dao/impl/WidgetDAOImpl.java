package com.orangeleap.webtools.dao.impl;

import com.orangeleap.webtools.dao.WidgetDAO;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.domain.WidgetExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class WidgetDAOImpl extends SqlMapClientDaoSupport implements WidgetDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public WidgetDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public int countWidgetByExample(WidgetExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("WIDGET.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public int deleteWidgetByExample(WidgetExample example) {
        int rows = getSqlMapClientTemplate().delete("WIDGET.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public int deleteWidgetByPrimaryKey(Long widgetId) {
        Widget key = new Widget();
        key.setWidgetId(widgetId);
        int rows = getSqlMapClientTemplate().delete("WIDGET.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void insertWidget(Widget record) {
        getSqlMapClientTemplate().insert("WIDGET.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void insertWidgetSelective(Widget record) {
        getSqlMapClientTemplate().insert("WIDGET.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    @SuppressWarnings("unchecked")
    public List<Widget> selectWidgetByExample(WidgetExample example) {
        List<Widget> list = getSqlMapClientTemplate().queryForList("WIDGET.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public Widget selectWidgetByPrimaryKey(Long widgetId) {
        Widget key = new Widget();
        key.setWidgetId(widgetId);
        Widget record = (Widget) getSqlMapClientTemplate().queryForObject("WIDGET.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public int updateWidgetByExampleSelective(Widget record, WidgetExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("WIDGET.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public int updateWidgetByExample(Widget record, WidgetExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("WIDGET.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public int updateWidgetByPrimaryKeySelective(Widget record) {
        int rows = getSqlMapClientTemplate().update("WIDGET.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public int updateWidgetByPrimaryKey(Widget record) {
        int rows = getSqlMapClientTemplate().update("WIDGET.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    private static class UpdateByExampleParms extends WidgetExample {
        private Object record;

        public UpdateByExampleParms(Object record, WidgetExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}