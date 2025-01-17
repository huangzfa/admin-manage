package com.duobei.core.operation.activity.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityTypeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public ActivityTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAtIdIsNull() {
            addCriterion("a.at_id is null");
            return (Criteria) this;
        }

        public Criteria andAtIdIsNotNull() {
            addCriterion("a.at_id is not null");
            return (Criteria) this;
        }

        public Criteria andAtIdEqualTo(Integer value) {
            addCriterion("a.at_id =", value, "atId");
            return (Criteria) this;
        }

        public Criteria andAtIdNotEqualTo(Integer value) {
            addCriterion("a.at_id <>", value, "atId");
            return (Criteria) this;
        }

        public Criteria andAtIdGreaterThan(Integer value) {
            addCriterion("a.at_id >", value, "atId");
            return (Criteria) this;
        }

        public Criteria andAtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.at_id >=", value, "atId");
            return (Criteria) this;
        }

        public Criteria andAtIdLessThan(Integer value) {
            addCriterion("a.at_id <", value, "atId");
            return (Criteria) this;
        }

        public Criteria andAtIdLessThanOrEqualTo(Integer value) {
            addCriterion("a.at_id <=", value, "atId");
            return (Criteria) this;
        }

        public Criteria andAtIdIn(List<Integer> values) {
            addCriterion("a.at_id in", values, "atId");
            return (Criteria) this;
        }

        public Criteria andAtIdNotIn(List<Integer> values) {
            addCriterion("a.at_id not in", values, "atId");
            return (Criteria) this;
        }

        public Criteria andAtIdBetween(Integer value1, Integer value2) {
            addCriterion("a.at_id between", value1, value2, "atId");
            return (Criteria) this;
        }

        public Criteria andAtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("a.at_id not between", value1, value2, "atId");
            return (Criteria) this;
        }

        public Criteria andAtCodeIsNull() {
            addCriterion("a.at_code is null");
            return (Criteria) this;
        }

        public Criteria andAtCodeIsNotNull() {
            addCriterion("a.at_code is not null");
            return (Criteria) this;
        }

        public Criteria andAtCodeEqualTo(String value) {
            addCriterion("a.at_code =", value, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtCodeNotEqualTo(String value) {
            addCriterion("a.at_code <>", value, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtCodeGreaterThan(String value) {
            addCriterion("a.at_code >", value, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtCodeGreaterThanOrEqualTo(String value) {
            addCriterion("a.at_code >=", value, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtCodeLessThan(String value) {
            addCriterion("a.at_code <", value, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtCodeLessThanOrEqualTo(String value) {
            addCriterion("a.at_code <=", value, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtCodeLike(String value) {
            addCriterion("a.at_code like", value, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtCodeNotLike(String value) {
            addCriterion("a.at_code not like", value, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtCodeIn(List<String> values) {
            addCriterion("a.at_code in", values, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtCodeNotIn(List<String> values) {
            addCriterion("a.at_code not in", values, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtCodeBetween(String value1, String value2) {
            addCriterion("a.at_code between", value1, value2, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtCodeNotBetween(String value1, String value2) {
            addCriterion("a.at_code not between", value1, value2, "atCode");
            return (Criteria) this;
        }

        public Criteria andAtNameIsNull() {
            addCriterion("a.at_name is null");
            return (Criteria) this;
        }

        public Criteria andAtNameIsNotNull() {
            addCriterion("a.at_name is not null");
            return (Criteria) this;
        }

        public Criteria andAtNameEqualTo(String value) {
            addCriterion("a.at_name =", value, "atName");
            return (Criteria) this;
        }

        public Criteria andAtNameNotEqualTo(String value) {
            addCriterion("a.at_name <>", value, "atName");
            return (Criteria) this;
        }

        public Criteria andAtNameGreaterThan(String value) {
            addCriterion("a.at_name >", value, "atName");
            return (Criteria) this;
        }

        public Criteria andAtNameGreaterThanOrEqualTo(String value) {
            addCriterion("a.at_name >=", value, "atName");
            return (Criteria) this;
        }

        public Criteria andAtNameLessThan(String value) {
            addCriterion("a.at_name <", value, "atName");
            return (Criteria) this;
        }

        public Criteria andAtNameLessThanOrEqualTo(String value) {
            addCriterion("a.at_name <=", value, "atName");
            return (Criteria) this;
        }

        public Criteria andAtNameLike(String value) {
            addCriterion("a.at_name like", value, "atName");
            return (Criteria) this;
        }

        public Criteria andAtNameNotLike(String value) {
            addCriterion("a.at_name not like", value, "atName");
            return (Criteria) this;
        }

        public Criteria andAtNameIn(List<String> values) {
            addCriterion("a.at_name in", values, "atName");
            return (Criteria) this;
        }

        public Criteria andAtNameNotIn(List<String> values) {
            addCriterion("a.at_name not in", values, "atName");
            return (Criteria) this;
        }

        public Criteria andAtNameBetween(String value1, String value2) {
            addCriterion("a.at_name between", value1, value2, "atName");
            return (Criteria) this;
        }

        public Criteria andAtNameNotBetween(String value1, String value2) {
            addCriterion("a.at_name not between", value1, value2, "atName");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("a.is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("a.is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("a.is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("a.is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("a.is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("a.is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("a.is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("a.is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("a.is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("a.is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("a.is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("a.add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("a.add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("a.add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("a.add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("a.add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("a.add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("a.add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("a.add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("a.add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("a.add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("a.add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("a.add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("a.modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("a.modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("a.modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("a.modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("a.modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("a.modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("a.modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("a.modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("a.modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("a.modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("a.modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("a.modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdIsNull() {
            addCriterion("a.add_operator_id is null");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdIsNotNull() {
            addCriterion("a.add_operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdEqualTo(Integer value) {
            addCriterion("a.add_operator_id =", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdNotEqualTo(Integer value) {
            addCriterion("a.add_operator_id <>", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdGreaterThan(Integer value) {
            addCriterion("a.add_operator_id >", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.add_operator_id >=", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdLessThan(Integer value) {
            addCriterion("a.add_operator_id <", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("a.add_operator_id <=", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdIn(List<Integer> values) {
            addCriterion("a.add_operator_id in", values, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdNotIn(List<Integer> values) {
            addCriterion("a.add_operator_id not in", values, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("a.add_operator_id between", value1, value2, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("a.add_operator_id not between", value1, value2, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdIsNull() {
            addCriterion("a.modify_operator_id is null");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdIsNotNull() {
            addCriterion("a.modify_operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdEqualTo(Integer value) {
            addCriterion("a.modify_operator_id =", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdNotEqualTo(Integer value) {
            addCriterion("a.modify_operator_id <>", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdGreaterThan(Integer value) {
            addCriterion("a.modify_operator_id >", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.modify_operator_id >=", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdLessThan(Integer value) {
            addCriterion("a.modify_operator_id <", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("a.modify_operator_id <=", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdIn(List<Integer> values) {
            addCriterion("a.modify_operator_id in", values, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdNotIn(List<Integer> values) {
            addCriterion("a.modify_operator_id not in", values, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("a.modify_operator_id between", value1, value2, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("a.modify_operator_id not between", value1, value2, "modifyOperatorId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yy_activity_type
     *
     * @mbg.generated do_not_delete_during_merge Fri Apr 12 10:45:05 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yy_activity_type
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}