package com.duobei.core.operation.activity.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityHongbaoPrizeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_hongbao_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_hongbao_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_hongbao_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_hongbao_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public ActivityHongbaoPrizeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_hongbao_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_hongbao_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_hongbao_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_hongbao_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_hongbao_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_hongbao_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_hongbao_prize
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
     * This method corresponds to the database table yy_activity_hongbao_prize
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
     * This method corresponds to the database table yy_activity_hongbao_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_hongbao_prize
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
     * This class corresponds to the database table yy_activity_hongbao_prize
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

        public Criteria andActPrizeIdIsNull() {
            addCriterion("a.act_prize_id is null");
            return (Criteria) this;
        }

        public Criteria andActPrizeIdIsNotNull() {
            addCriterion("a.act_prize_id is not null");
            return (Criteria) this;
        }

        public Criteria andActPrizeIdEqualTo(Integer value) {
            addCriterion("a.act_prize_id =", value, "actPrizeId");
            return (Criteria) this;
        }

        public Criteria andActPrizeIdNotEqualTo(Integer value) {
            addCriterion("a.act_prize_id <>", value, "actPrizeId");
            return (Criteria) this;
        }

        public Criteria andActPrizeIdGreaterThan(Integer value) {
            addCriterion("a.act_prize_id >", value, "actPrizeId");
            return (Criteria) this;
        }

        public Criteria andActPrizeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.act_prize_id >=", value, "actPrizeId");
            return (Criteria) this;
        }

        public Criteria andActPrizeIdLessThan(Integer value) {
            addCriterion("a.act_prize_id <", value, "actPrizeId");
            return (Criteria) this;
        }

        public Criteria andActPrizeIdLessThanOrEqualTo(Integer value) {
            addCriterion("a.act_prize_id <=", value, "actPrizeId");
            return (Criteria) this;
        }

        public Criteria andActPrizeIdIn(List<Integer> values) {
            addCriterion("a.act_prize_id in", values, "actPrizeId");
            return (Criteria) this;
        }

        public Criteria andActPrizeIdNotIn(List<Integer> values) {
            addCriterion("a.act_prize_id not in", values, "actPrizeId");
            return (Criteria) this;
        }

        public Criteria andActPrizeIdBetween(Integer value1, Integer value2) {
            addCriterion("a.act_prize_id between", value1, value2, "actPrizeId");
            return (Criteria) this;
        }

        public Criteria andActPrizeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("a.act_prize_id not between", value1, value2, "actPrizeId");
            return (Criteria) this;
        }

        public Criteria andActIdIsNull() {
            addCriterion("a.act_id is null");
            return (Criteria) this;
        }

        public Criteria andActIdIsNotNull() {
            addCriterion("a.act_id is not null");
            return (Criteria) this;
        }

        public Criteria andActIdEqualTo(Integer value) {
            addCriterion("a.act_id =", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdNotEqualTo(Integer value) {
            addCriterion("a.act_id <>", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdGreaterThan(Integer value) {
            addCriterion("a.act_id >", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.act_id >=", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdLessThan(Integer value) {
            addCriterion("a.act_id <", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdLessThanOrEqualTo(Integer value) {
            addCriterion("a.act_id <=", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdIn(List<Integer> values) {
            addCriterion("a.act_id in", values, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdNotIn(List<Integer> values) {
            addCriterion("a.act_id not in", values, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdBetween(Integer value1, Integer value2) {
            addCriterion("a.act_id between", value1, value2, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdNotBetween(Integer value1, Integer value2) {
            addCriterion("a.act_id not between", value1, value2, "actId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdIsNull() {
            addCriterion("a.prize_id is null");
            return (Criteria) this;
        }

        public Criteria andPrizeIdIsNotNull() {
            addCriterion("a.prize_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeIdEqualTo(Long value) {
            addCriterion("a.prize_id =", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdNotEqualTo(Long value) {
            addCriterion("a.prize_id <>", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdGreaterThan(Long value) {
            addCriterion("a.prize_id >", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("a.prize_id >=", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdLessThan(Long value) {
            addCriterion("a.prize_id <", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdLessThanOrEqualTo(Long value) {
            addCriterion("a.prize_id <=", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdIn(List<Long> values) {
            addCriterion("a.prize_id in", values, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdNotIn(List<Long> values) {
            addCriterion("a.prize_id not in", values, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdBetween(Long value1, Long value2) {
            addCriterion("a.prize_id between", value1, value2, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdNotBetween(Long value1, Long value2) {
            addCriterion("a.prize_id not between", value1, value2, "prizeId");
            return (Criteria) this;
        }

        public Criteria andDaysIsNull() {
            addCriterion("a.days is null");
            return (Criteria) this;
        }

        public Criteria andDaysIsNotNull() {
            addCriterion("a.days is not null");
            return (Criteria) this;
        }

        public Criteria andDaysEqualTo(Integer value) {
            addCriterion("a.days =", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotEqualTo(Integer value) {
            addCriterion("a.days <>", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThan(Integer value) {
            addCriterion("a.days >", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.days >=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThan(Integer value) {
            addCriterion("a.days <", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThanOrEqualTo(Integer value) {
            addCriterion("a.days <=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysIn(List<Integer> values) {
            addCriterion("a.days in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotIn(List<Integer> values) {
            addCriterion("a.days not in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysBetween(Integer value1, Integer value2) {
            addCriterion("a.days between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("a.days not between", value1, value2, "days");
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
     * This class corresponds to the database table yy_activity_hongbao_prize
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
     * This class corresponds to the database table yy_activity_hongbao_prize
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