package com.orangeleap.webtools.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WidgetExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public WidgetExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    protected WidgetExample(WidgetExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
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
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table WIDGET
     *
     * @ibatorgenerated Tue Jul 27 17:41:07 CDT 2010
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

        public Criteria andWidgetIdIsNull() {
            addCriterion("WIDGET_ID is null");
            return this;
        }

        public Criteria andWidgetIdIsNotNull() {
            addCriterion("WIDGET_ID is not null");
            return this;
        }

        public Criteria andWidgetIdEqualTo(Long value) {
            addCriterion("WIDGET_ID =", value, "widgetId");
            return this;
        }

        public Criteria andWidgetIdNotEqualTo(Long value) {
            addCriterion("WIDGET_ID <>", value, "widgetId");
            return this;
        }

        public Criteria andWidgetIdGreaterThan(Long value) {
            addCriterion("WIDGET_ID >", value, "widgetId");
            return this;
        }

        public Criteria andWidgetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("WIDGET_ID >=", value, "widgetId");
            return this;
        }

        public Criteria andWidgetIdLessThan(Long value) {
            addCriterion("WIDGET_ID <", value, "widgetId");
            return this;
        }

        public Criteria andWidgetIdLessThanOrEqualTo(Long value) {
            addCriterion("WIDGET_ID <=", value, "widgetId");
            return this;
        }

        public Criteria andWidgetIdIn(List<Long> values) {
            addCriterion("WIDGET_ID in", values, "widgetId");
            return this;
        }

        public Criteria andWidgetIdNotIn(List<Long> values) {
            addCriterion("WIDGET_ID not in", values, "widgetId");
            return this;
        }

        public Criteria andWidgetIdBetween(Long value1, Long value2) {
            addCriterion("WIDGET_ID between", value1, value2, "widgetId");
            return this;
        }

        public Criteria andWidgetIdNotBetween(Long value1, Long value2) {
            addCriterion("WIDGET_ID not between", value1, value2, "widgetId");
            return this;
        }

        public Criteria andWidgetGuidIsNull() {
            addCriterion("WIDGET_GUID is null");
            return this;
        }

        public Criteria andWidgetGuidIsNotNull() {
            addCriterion("WIDGET_GUID is not null");
            return this;
        }

        public Criteria andWidgetGuidEqualTo(String value) {
            addCriterion("WIDGET_GUID =", value, "widgetGuid");
            return this;
        }

        public Criteria andWidgetGuidNotEqualTo(String value) {
            addCriterion("WIDGET_GUID <>", value, "widgetGuid");
            return this;
        }

        public Criteria andWidgetGuidGreaterThan(String value) {
            addCriterion("WIDGET_GUID >", value, "widgetGuid");
            return this;
        }

        public Criteria andWidgetGuidGreaterThanOrEqualTo(String value) {
            addCriterion("WIDGET_GUID >=", value, "widgetGuid");
            return this;
        }

        public Criteria andWidgetGuidLessThan(String value) {
            addCriterion("WIDGET_GUID <", value, "widgetGuid");
            return this;
        }

        public Criteria andWidgetGuidLessThanOrEqualTo(String value) {
            addCriterion("WIDGET_GUID <=", value, "widgetGuid");
            return this;
        }

        public Criteria andWidgetGuidLike(String value) {
            addCriterion("WIDGET_GUID like", value, "widgetGuid");
            return this;
        }

        public Criteria andWidgetGuidNotLike(String value) {
            addCriterion("WIDGET_GUID not like", value, "widgetGuid");
            return this;
        }

        public Criteria andWidgetGuidIn(List<String> values) {
            addCriterion("WIDGET_GUID in", values, "widgetGuid");
            return this;
        }

        public Criteria andWidgetGuidNotIn(List<String> values) {
            addCriterion("WIDGET_GUID not in", values, "widgetGuid");
            return this;
        }

        public Criteria andWidgetGuidBetween(String value1, String value2) {
            addCriterion("WIDGET_GUID between", value1, value2, "widgetGuid");
            return this;
        }

        public Criteria andWidgetGuidNotBetween(String value1, String value2) {
            addCriterion("WIDGET_GUID not between", value1, value2, "widgetGuid");
            return this;
        }

        public Criteria andWidgetUsernameIsNull() {
            addCriterion("WIDGET_USERNAME is null");
            return this;
        }

        public Criteria andWidgetUsernameIsNotNull() {
            addCriterion("WIDGET_USERNAME is not null");
            return this;
        }

        public Criteria andWidgetUsernameEqualTo(String value) {
            addCriterion("WIDGET_USERNAME =", value, "widgetUsername");
            return this;
        }

        public Criteria andWidgetUsernameNotEqualTo(String value) {
            addCriterion("WIDGET_USERNAME <>", value, "widgetUsername");
            return this;
        }

        public Criteria andWidgetUsernameGreaterThan(String value) {
            addCriterion("WIDGET_USERNAME >", value, "widgetUsername");
            return this;
        }

        public Criteria andWidgetUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("WIDGET_USERNAME >=", value, "widgetUsername");
            return this;
        }

        public Criteria andWidgetUsernameLessThan(String value) {
            addCriterion("WIDGET_USERNAME <", value, "widgetUsername");
            return this;
        }

        public Criteria andWidgetUsernameLessThanOrEqualTo(String value) {
            addCriterion("WIDGET_USERNAME <=", value, "widgetUsername");
            return this;
        }

        public Criteria andWidgetUsernameLike(String value) {
            addCriterion("WIDGET_USERNAME like", value, "widgetUsername");
            return this;
        }

        public Criteria andWidgetUsernameNotLike(String value) {
            addCriterion("WIDGET_USERNAME not like", value, "widgetUsername");
            return this;
        }

        public Criteria andWidgetUsernameIn(List<String> values) {
            addCriterion("WIDGET_USERNAME in", values, "widgetUsername");
            return this;
        }

        public Criteria andWidgetUsernameNotIn(List<String> values) {
            addCriterion("WIDGET_USERNAME not in", values, "widgetUsername");
            return this;
        }

        public Criteria andWidgetUsernameBetween(String value1, String value2) {
            addCriterion("WIDGET_USERNAME between", value1, value2, "widgetUsername");
            return this;
        }

        public Criteria andWidgetUsernameNotBetween(String value1, String value2) {
            addCriterion("WIDGET_USERNAME not between", value1, value2, "widgetUsername");
            return this;
        }

        public Criteria andWidgetPasswordIsNull() {
            addCriterion("WIDGET_PASSWORD is null");
            return this;
        }

        public Criteria andWidgetPasswordIsNotNull() {
            addCriterion("WIDGET_PASSWORD is not null");
            return this;
        }

        public Criteria andWidgetPasswordEqualTo(String value) {
            addCriterion("WIDGET_PASSWORD =", value, "widgetPassword");
            return this;
        }

        public Criteria andWidgetPasswordNotEqualTo(String value) {
            addCriterion("WIDGET_PASSWORD <>", value, "widgetPassword");
            return this;
        }

        public Criteria andWidgetPasswordGreaterThan(String value) {
            addCriterion("WIDGET_PASSWORD >", value, "widgetPassword");
            return this;
        }

        public Criteria andWidgetPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("WIDGET_PASSWORD >=", value, "widgetPassword");
            return this;
        }

        public Criteria andWidgetPasswordLessThan(String value) {
            addCriterion("WIDGET_PASSWORD <", value, "widgetPassword");
            return this;
        }

        public Criteria andWidgetPasswordLessThanOrEqualTo(String value) {
            addCriterion("WIDGET_PASSWORD <=", value, "widgetPassword");
            return this;
        }

        public Criteria andWidgetPasswordLike(String value) {
            addCriterion("WIDGET_PASSWORD like", value, "widgetPassword");
            return this;
        }

        public Criteria andWidgetPasswordNotLike(String value) {
            addCriterion("WIDGET_PASSWORD not like", value, "widgetPassword");
            return this;
        }

        public Criteria andWidgetPasswordIn(List<String> values) {
            addCriterion("WIDGET_PASSWORD in", values, "widgetPassword");
            return this;
        }

        public Criteria andWidgetPasswordNotIn(List<String> values) {
            addCriterion("WIDGET_PASSWORD not in", values, "widgetPassword");
            return this;
        }

        public Criteria andWidgetPasswordBetween(String value1, String value2) {
            addCriterion("WIDGET_PASSWORD between", value1, value2, "widgetPassword");
            return this;
        }

        public Criteria andWidgetPasswordNotBetween(String value1, String value2) {
            addCriterion("WIDGET_PASSWORD not between", value1, value2, "widgetPassword");
            return this;
        }

        public Criteria andWidgetErrorCountIsNull() {
            addCriterion("WIDGET_ERROR_COUNT is null");
            return this;
        }

        public Criteria andWidgetErrorCountIsNotNull() {
            addCriterion("WIDGET_ERROR_COUNT is not null");
            return this;
        }

        public Criteria andWidgetErrorCountEqualTo(Long value) {
            addCriterion("WIDGET_ERROR_COUNT =", value, "widgetErrorCount");
            return this;
        }

        public Criteria andWidgetErrorCountNotEqualTo(Long value) {
            addCriterion("WIDGET_ERROR_COUNT <>", value, "widgetErrorCount");
            return this;
        }

        public Criteria andWidgetErrorCountGreaterThan(Long value) {
            addCriterion("WIDGET_ERROR_COUNT >", value, "widgetErrorCount");
            return this;
        }

        public Criteria andWidgetErrorCountGreaterThanOrEqualTo(Long value) {
            addCriterion("WIDGET_ERROR_COUNT >=", value, "widgetErrorCount");
            return this;
        }

        public Criteria andWidgetErrorCountLessThan(Long value) {
            addCriterion("WIDGET_ERROR_COUNT <", value, "widgetErrorCount");
            return this;
        }

        public Criteria andWidgetErrorCountLessThanOrEqualTo(Long value) {
            addCriterion("WIDGET_ERROR_COUNT <=", value, "widgetErrorCount");
            return this;
        }

        public Criteria andWidgetErrorCountIn(List<Long> values) {
            addCriterion("WIDGET_ERROR_COUNT in", values, "widgetErrorCount");
            return this;
        }

        public Criteria andWidgetErrorCountNotIn(List<Long> values) {
            addCriterion("WIDGET_ERROR_COUNT not in", values, "widgetErrorCount");
            return this;
        }

        public Criteria andWidgetErrorCountBetween(Long value1, Long value2) {
            addCriterion("WIDGET_ERROR_COUNT between", value1, value2, "widgetErrorCount");
            return this;
        }

        public Criteria andWidgetErrorCountNotBetween(Long value1, Long value2) {
            addCriterion("WIDGET_ERROR_COUNT not between", value1, value2, "widgetErrorCount");
            return this;
        }

        public Criteria andWidgetCreateDateIsNull() {
            addCriterion("WIDGET_CREATE_DATE is null");
            return this;
        }

        public Criteria andWidgetCreateDateIsNotNull() {
            addCriterion("WIDGET_CREATE_DATE is not null");
            return this;
        }

        public Criteria andWidgetCreateDateEqualTo(Date value) {
            addCriterion("WIDGET_CREATE_DATE =", value, "widgetCreateDate");
            return this;
        }

        public Criteria andWidgetCreateDateNotEqualTo(Date value) {
            addCriterion("WIDGET_CREATE_DATE <>", value, "widgetCreateDate");
            return this;
        }

        public Criteria andWidgetCreateDateGreaterThan(Date value) {
            addCriterion("WIDGET_CREATE_DATE >", value, "widgetCreateDate");
            return this;
        }

        public Criteria andWidgetCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("WIDGET_CREATE_DATE >=", value, "widgetCreateDate");
            return this;
        }

        public Criteria andWidgetCreateDateLessThan(Date value) {
            addCriterion("WIDGET_CREATE_DATE <", value, "widgetCreateDate");
            return this;
        }

        public Criteria andWidgetCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("WIDGET_CREATE_DATE <=", value, "widgetCreateDate");
            return this;
        }

        public Criteria andWidgetCreateDateIn(List<Date> values) {
            addCriterion("WIDGET_CREATE_DATE in", values, "widgetCreateDate");
            return this;
        }

        public Criteria andWidgetCreateDateNotIn(List<Date> values) {
            addCriterion("WIDGET_CREATE_DATE not in", values, "widgetCreateDate");
            return this;
        }

        public Criteria andWidgetCreateDateBetween(Date value1, Date value2) {
            addCriterion("WIDGET_CREATE_DATE between", value1, value2, "widgetCreateDate");
            return this;
        }

        public Criteria andWidgetCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("WIDGET_CREATE_DATE not between", value1, value2, "widgetCreateDate");
            return this;
        }

        public Criteria andWidgetHtmlIsNull() {
            addCriterion("WIDGET_HTML is null");
            return this;
        }

        public Criteria andWidgetHtmlIsNotNull() {
            addCriterion("WIDGET_HTML is not null");
            return this;
        }

        public Criteria andWidgetHtmlEqualTo(String value) {
            addCriterion("WIDGET_HTML =", value, "widgetHtml");
            return this;
        }

        public Criteria andWidgetHtmlNotEqualTo(String value) {
            addCriterion("WIDGET_HTML <>", value, "widgetHtml");
            return this;
        }

        public Criteria andWidgetHtmlGreaterThan(String value) {
            addCriterion("WIDGET_HTML >", value, "widgetHtml");
            return this;
        }

        public Criteria andWidgetHtmlGreaterThanOrEqualTo(String value) {
            addCriterion("WIDGET_HTML >=", value, "widgetHtml");
            return this;
        }

        public Criteria andWidgetHtmlLessThan(String value) {
            addCriterion("WIDGET_HTML <", value, "widgetHtml");
            return this;
        }

        public Criteria andWidgetHtmlLessThanOrEqualTo(String value) {
            addCriterion("WIDGET_HTML <=", value, "widgetHtml");
            return this;
        }

        public Criteria andWidgetHtmlLike(String value) {
            addCriterion("WIDGET_HTML like", value, "widgetHtml");
            return this;
        }

        public Criteria andWidgetHtmlNotLike(String value) {
            addCriterion("WIDGET_HTML not like", value, "widgetHtml");
            return this;
        }

        public Criteria andWidgetHtmlIn(List<String> values) {
            addCriterion("WIDGET_HTML in", values, "widgetHtml");
            return this;
        }

        public Criteria andWidgetHtmlNotIn(List<String> values) {
            addCriterion("WIDGET_HTML not in", values, "widgetHtml");
            return this;
        }

        public Criteria andWidgetHtmlBetween(String value1, String value2) {
            addCriterion("WIDGET_HTML between", value1, value2, "widgetHtml");
            return this;
        }

        public Criteria andWidgetHtmlNotBetween(String value1, String value2) {
            addCriterion("WIDGET_HTML not between", value1, value2, "widgetHtml");
            return this;
        }

        public Criteria andWidgetViewCountIsNull() {
            addCriterion("WIDGET_VIEW_COUNT is null");
            return this;
        }

        public Criteria andWidgetViewCountIsNotNull() {
            addCriterion("WIDGET_VIEW_COUNT is not null");
            return this;
        }

        public Criteria andWidgetViewCountEqualTo(Long value) {
            addCriterion("WIDGET_VIEW_COUNT =", value, "widgetViewCount");
            return this;
        }

        public Criteria andWidgetViewCountNotEqualTo(Long value) {
            addCriterion("WIDGET_VIEW_COUNT <>", value, "widgetViewCount");
            return this;
        }

        public Criteria andWidgetViewCountGreaterThan(Long value) {
            addCriterion("WIDGET_VIEW_COUNT >", value, "widgetViewCount");
            return this;
        }

        public Criteria andWidgetViewCountGreaterThanOrEqualTo(Long value) {
            addCriterion("WIDGET_VIEW_COUNT >=", value, "widgetViewCount");
            return this;
        }

        public Criteria andWidgetViewCountLessThan(Long value) {
            addCriterion("WIDGET_VIEW_COUNT <", value, "widgetViewCount");
            return this;
        }

        public Criteria andWidgetViewCountLessThanOrEqualTo(Long value) {
            addCriterion("WIDGET_VIEW_COUNT <=", value, "widgetViewCount");
            return this;
        }

        public Criteria andWidgetViewCountIn(List<Long> values) {
            addCriterion("WIDGET_VIEW_COUNT in", values, "widgetViewCount");
            return this;
        }

        public Criteria andWidgetViewCountNotIn(List<Long> values) {
            addCriterion("WIDGET_VIEW_COUNT not in", values, "widgetViewCount");
            return this;
        }

        public Criteria andWidgetViewCountBetween(Long value1, Long value2) {
            addCriterion("WIDGET_VIEW_COUNT between", value1, value2, "widgetViewCount");
            return this;
        }

        public Criteria andWidgetViewCountNotBetween(Long value1, Long value2) {
            addCriterion("WIDGET_VIEW_COUNT not between", value1, value2, "widgetViewCount");
            return this;
        }

        public Criteria andWidgetTypeIsNull() {
            addCriterion("WIDGET_TYPE is null");
            return this;
        }

        public Criteria andWidgetTypeIsNotNull() {
            addCriterion("WIDGET_TYPE is not null");
            return this;
        }

        public Criteria andWidgetTypeEqualTo(String value) {
            addCriterion("WIDGET_TYPE =", value, "widgetType");
            return this;
        }

        public Criteria andWidgetTypeNotEqualTo(String value) {
            addCriterion("WIDGET_TYPE <>", value, "widgetType");
            return this;
        }

        public Criteria andWidgetTypeGreaterThan(String value) {
            addCriterion("WIDGET_TYPE >", value, "widgetType");
            return this;
        }

        public Criteria andWidgetTypeGreaterThanOrEqualTo(String value) {
            addCriterion("WIDGET_TYPE >=", value, "widgetType");
            return this;
        }

        public Criteria andWidgetTypeLessThan(String value) {
            addCriterion("WIDGET_TYPE <", value, "widgetType");
            return this;
        }

        public Criteria andWidgetTypeLessThanOrEqualTo(String value) {
            addCriterion("WIDGET_TYPE <=", value, "widgetType");
            return this;
        }

        public Criteria andWidgetTypeLike(String value) {
            addCriterion("WIDGET_TYPE like", value, "widgetType");
            return this;
        }

        public Criteria andWidgetTypeNotLike(String value) {
            addCriterion("WIDGET_TYPE not like", value, "widgetType");
            return this;
        }

        public Criteria andWidgetTypeIn(List<String> values) {
            addCriterion("WIDGET_TYPE in", values, "widgetType");
            return this;
        }

        public Criteria andWidgetTypeNotIn(List<String> values) {
            addCriterion("WIDGET_TYPE not in", values, "widgetType");
            return this;
        }

        public Criteria andWidgetTypeBetween(String value1, String value2) {
            addCriterion("WIDGET_TYPE between", value1, value2, "widgetType");
            return this;
        }

        public Criteria andWidgetTypeNotBetween(String value1, String value2) {
            addCriterion("WIDGET_TYPE not between", value1, value2, "widgetType");
            return this;
        }

        public Criteria andMotivationCodeIsNull() {
            addCriterion("MOTIVATION_CODE is null");
            return this;
        }

        public Criteria andMotivationCodeIsNotNull() {
            addCriterion("MOTIVATION_CODE is not null");
            return this;
        }

        public Criteria andMotivationCodeEqualTo(String value) {
            addCriterion("MOTIVATION_CODE =", value, "motivationCode");
            return this;
        }

        public Criteria andMotivationCodeNotEqualTo(String value) {
            addCriterion("MOTIVATION_CODE <>", value, "motivationCode");
            return this;
        }

        public Criteria andMotivationCodeGreaterThan(String value) {
            addCriterion("MOTIVATION_CODE >", value, "motivationCode");
            return this;
        }

        public Criteria andMotivationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MOTIVATION_CODE >=", value, "motivationCode");
            return this;
        }

        public Criteria andMotivationCodeLessThan(String value) {
            addCriterion("MOTIVATION_CODE <", value, "motivationCode");
            return this;
        }

        public Criteria andMotivationCodeLessThanOrEqualTo(String value) {
            addCriterion("MOTIVATION_CODE <=", value, "motivationCode");
            return this;
        }

        public Criteria andMotivationCodeLike(String value) {
            addCriterion("MOTIVATION_CODE like", value, "motivationCode");
            return this;
        }

        public Criteria andMotivationCodeNotLike(String value) {
            addCriterion("MOTIVATION_CODE not like", value, "motivationCode");
            return this;
        }

        public Criteria andMotivationCodeIn(List<String> values) {
            addCriterion("MOTIVATION_CODE in", values, "motivationCode");
            return this;
        }

        public Criteria andMotivationCodeNotIn(List<String> values) {
            addCriterion("MOTIVATION_CODE not in", values, "motivationCode");
            return this;
        }

        public Criteria andMotivationCodeBetween(String value1, String value2) {
            addCriterion("MOTIVATION_CODE between", value1, value2, "motivationCode");
            return this;
        }

        public Criteria andMotivationCodeNotBetween(String value1, String value2) {
            addCriterion("MOTIVATION_CODE not between", value1, value2, "motivationCode");
            return this;
        }

        public Criteria andProjectCodeIsNull() {
            addCriterion("PROJECT_CODE is null");
            return this;
        }

        public Criteria andProjectCodeIsNotNull() {
            addCriterion("PROJECT_CODE is not null");
            return this;
        }

        public Criteria andProjectCodeEqualTo(String value) {
            addCriterion("PROJECT_CODE =", value, "projectCode");
            return this;
        }

        public Criteria andProjectCodeNotEqualTo(String value) {
            addCriterion("PROJECT_CODE <>", value, "projectCode");
            return this;
        }

        public Criteria andProjectCodeGreaterThan(String value) {
            addCriterion("PROJECT_CODE >", value, "projectCode");
            return this;
        }

        public Criteria andProjectCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_CODE >=", value, "projectCode");
            return this;
        }

        public Criteria andProjectCodeLessThan(String value) {
            addCriterion("PROJECT_CODE <", value, "projectCode");
            return this;
        }

        public Criteria andProjectCodeLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_CODE <=", value, "projectCode");
            return this;
        }

        public Criteria andProjectCodeLike(String value) {
            addCriterion("PROJECT_CODE like", value, "projectCode");
            return this;
        }

        public Criteria andProjectCodeNotLike(String value) {
            addCriterion("PROJECT_CODE not like", value, "projectCode");
            return this;
        }

        public Criteria andProjectCodeIn(List<String> values) {
            addCriterion("PROJECT_CODE in", values, "projectCode");
            return this;
        }

        public Criteria andProjectCodeNotIn(List<String> values) {
            addCriterion("PROJECT_CODE not in", values, "projectCode");
            return this;
        }

        public Criteria andProjectCodeBetween(String value1, String value2) {
            addCriterion("PROJECT_CODE between", value1, value2, "projectCode");
            return this;
        }

        public Criteria andProjectCodeNotBetween(String value1, String value2) {
            addCriterion("PROJECT_CODE not between", value1, value2, "projectCode");
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