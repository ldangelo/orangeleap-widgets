package com.orangeleap.donatenow.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomEntityWidgetExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    public CustomEntityWidgetExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    protected CustomEntityWidgetExample(CustomEntityWidgetExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table CUSTOM_ENTITY_WIDGET
     *
     * @ibatorgenerated Tue Jun 08 15:31:29 CDT 2010
     */
    public static class Criteria {
        protected List<String> criteriaWithoutValue;

        protected List<Map<String, Object>> criteriaWithSingleValue;

        protected List<Map<String, Object>> criteriaWithListValue;

        protected List<Map<String, Object>> criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList<String>();
            criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
            criteriaWithListValue = new ArrayList<Map<String, Object>>();
            criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List<String> getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List<Map<String, Object>> getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List<Map<String, Object>> getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List<Map<String, Object>> getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List<? extends Object> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List<Object> list = new ArrayList<Object>();
            list.add(value1);
            list.add(value2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andCustomEntityWidgetIdIsNull() {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID is null");
            return this;
        }

        public Criteria andCustomEntityWidgetIdIsNotNull() {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID is not null");
            return this;
        }

        public Criteria andCustomEntityWidgetIdEqualTo(Long value) {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID =", value, "customEntityWidgetId");
            return this;
        }

        public Criteria andCustomEntityWidgetIdNotEqualTo(Long value) {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID <>", value, "customEntityWidgetId");
            return this;
        }

        public Criteria andCustomEntityWidgetIdGreaterThan(Long value) {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID >", value, "customEntityWidgetId");
            return this;
        }

        public Criteria andCustomEntityWidgetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID >=", value, "customEntityWidgetId");
            return this;
        }

        public Criteria andCustomEntityWidgetIdLessThan(Long value) {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID <", value, "customEntityWidgetId");
            return this;
        }

        public Criteria andCustomEntityWidgetIdLessThanOrEqualTo(Long value) {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID <=", value, "customEntityWidgetId");
            return this;
        }

        public Criteria andCustomEntityWidgetIdIn(List<Long> values) {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID in", values, "customEntityWidgetId");
            return this;
        }

        public Criteria andCustomEntityWidgetIdNotIn(List<Long> values) {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID not in", values, "customEntityWidgetId");
            return this;
        }

        public Criteria andCustomEntityWidgetIdBetween(Long value1, Long value2) {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID between", value1, value2, "customEntityWidgetId");
            return this;
        }

        public Criteria andCustomEntityWidgetIdNotBetween(Long value1, Long value2) {
            addCriterion("CUSTOM_ENTITY_WIDGET_ID not between", value1, value2, "customEntityWidgetId");
            return this;
        }

        public Criteria andCustomEntityNameIsNull() {
            addCriterion("CUSTOM_ENTITY_NAME is null");
            return this;
        }

        public Criteria andCustomEntityNameIsNotNull() {
            addCriterion("CUSTOM_ENTITY_NAME is not null");
            return this;
        }

        public Criteria andCustomEntityNameEqualTo(String value) {
            addCriterion("CUSTOM_ENTITY_NAME =", value, "customEntityName");
            return this;
        }

        public Criteria andCustomEntityNameNotEqualTo(String value) {
            addCriterion("CUSTOM_ENTITY_NAME <>", value, "customEntityName");
            return this;
        }

        public Criteria andCustomEntityNameGreaterThan(String value) {
            addCriterion("CUSTOM_ENTITY_NAME >", value, "customEntityName");
            return this;
        }

        public Criteria andCustomEntityNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOM_ENTITY_NAME >=", value, "customEntityName");
            return this;
        }

        public Criteria andCustomEntityNameLessThan(String value) {
            addCriterion("CUSTOM_ENTITY_NAME <", value, "customEntityName");
            return this;
        }

        public Criteria andCustomEntityNameLessThanOrEqualTo(String value) {
            addCriterion("CUSTOM_ENTITY_NAME <=", value, "customEntityName");
            return this;
        }

        public Criteria andCustomEntityNameLike(String value) {
            addCriterion("CUSTOM_ENTITY_NAME like", value, "customEntityName");
            return this;
        }

        public Criteria andCustomEntityNameNotLike(String value) {
            addCriterion("CUSTOM_ENTITY_NAME not like", value, "customEntityName");
            return this;
        }

        public Criteria andCustomEntityNameIn(List<String> values) {
            addCriterion("CUSTOM_ENTITY_NAME in", values, "customEntityName");
            return this;
        }

        public Criteria andCustomEntityNameNotIn(List<String> values) {
            addCriterion("CUSTOM_ENTITY_NAME not in", values, "customEntityName");
            return this;
        }

        public Criteria andCustomEntityNameBetween(String value1, String value2) {
            addCriterion("CUSTOM_ENTITY_NAME between", value1, value2, "customEntityName");
            return this;
        }

        public Criteria andCustomEntityNameNotBetween(String value1, String value2) {
            addCriterion("CUSTOM_ENTITY_NAME not between", value1, value2, "customEntityName");
            return this;
        }
    }
}