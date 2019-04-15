package com.duobei.core.operation.activity.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityExchangeGoldExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_exchange_gold
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_exchange_gold
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_exchange_gold
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_exchange_gold
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public ActivityExchangeGoldExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_exchange_gold
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_exchange_gold
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_exchange_gold
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_exchange_gold
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_exchange_gold
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_exchange_gold
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_exchange_gold
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
     * This method corresponds to the database table yy_activity_exchange_gold
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
     * This method corresponds to the database table yy_activity_exchange_gold
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_exchange_gold
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
     * This class corresponds to the database table yy_activity_exchange_gold
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

        public Criteria andIdIsNull() {
            addCriterion("a.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("a.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("a.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("a.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("a.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("a.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("a.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("a.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("a.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("a.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("a.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("a.phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("a.phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("a.phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("a.phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("a.phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("a.phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("a.phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("a.phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("a.phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("a.phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("a.phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("a.phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("a.phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("a.phone not between", value1, value2, "phone");
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

        public Criteria andPrizeIdEqualTo(Integer value) {
            addCriterion("a.prize_id =", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdNotEqualTo(Integer value) {
            addCriterion("a.prize_id <>", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdGreaterThan(Integer value) {
            addCriterion("a.prize_id >", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.prize_id >=", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdLessThan(Integer value) {
            addCriterion("a.prize_id <", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdLessThanOrEqualTo(Integer value) {
            addCriterion("a.prize_id <=", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdIn(List<Integer> values) {
            addCriterion("a.prize_id in", values, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdNotIn(List<Integer> values) {
            addCriterion("a.prize_id not in", values, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdBetween(Integer value1, Integer value2) {
            addCriterion("a.prize_id between", value1, value2, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("a.prize_id not between", value1, value2, "prizeId");
            return (Criteria) this;
        }

        public Criteria andGoldNumberIsNull() {
            addCriterion("a.gold_number is null");
            return (Criteria) this;
        }

        public Criteria andGoldNumberIsNotNull() {
            addCriterion("a.gold_number is not null");
            return (Criteria) this;
        }

        public Criteria andGoldNumberEqualTo(Integer value) {
            addCriterion("a.gold_number =", value, "goldNumber");
            return (Criteria) this;
        }

        public Criteria andGoldNumberNotEqualTo(Integer value) {
            addCriterion("a.gold_number <>", value, "goldNumber");
            return (Criteria) this;
        }

        public Criteria andGoldNumberGreaterThan(Integer value) {
            addCriterion("a.gold_number >", value, "goldNumber");
            return (Criteria) this;
        }

        public Criteria andGoldNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.gold_number >=", value, "goldNumber");
            return (Criteria) this;
        }

        public Criteria andGoldNumberLessThan(Integer value) {
            addCriterion("a.gold_number <", value, "goldNumber");
            return (Criteria) this;
        }

        public Criteria andGoldNumberLessThanOrEqualTo(Integer value) {
            addCriterion("a.gold_number <=", value, "goldNumber");
            return (Criteria) this;
        }

        public Criteria andGoldNumberIn(List<Integer> values) {
            addCriterion("a.gold_number in", values, "goldNumber");
            return (Criteria) this;
        }

        public Criteria andGoldNumberNotIn(List<Integer> values) {
            addCriterion("a.gold_number not in", values, "goldNumber");
            return (Criteria) this;
        }

        public Criteria andGoldNumberBetween(Integer value1, Integer value2) {
            addCriterion("a.gold_number between", value1, value2, "goldNumber");
            return (Criteria) this;
        }

        public Criteria andGoldNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("a.gold_number not between", value1, value2, "goldNumber");
            return (Criteria) this;
        }

        public Criteria andExchangeNumIsNull() {
            addCriterion("a.exchange_num is null");
            return (Criteria) this;
        }

        public Criteria andExchangeNumIsNotNull() {
            addCriterion("a.exchange_num is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeNumEqualTo(Integer value) {
            addCriterion("a.exchange_num =", value, "exchangeNum");
            return (Criteria) this;
        }

        public Criteria andExchangeNumNotEqualTo(Integer value) {
            addCriterion("a.exchange_num <>", value, "exchangeNum");
            return (Criteria) this;
        }

        public Criteria andExchangeNumGreaterThan(Integer value) {
            addCriterion("a.exchange_num >", value, "exchangeNum");
            return (Criteria) this;
        }

        public Criteria andExchangeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.exchange_num >=", value, "exchangeNum");
            return (Criteria) this;
        }

        public Criteria andExchangeNumLessThan(Integer value) {
            addCriterion("a.exchange_num <", value, "exchangeNum");
            return (Criteria) this;
        }

        public Criteria andExchangeNumLessThanOrEqualTo(Integer value) {
            addCriterion("a.exchange_num <=", value, "exchangeNum");
            return (Criteria) this;
        }

        public Criteria andExchangeNumIn(List<Integer> values) {
            addCriterion("a.exchange_num in", values, "exchangeNum");
            return (Criteria) this;
        }

        public Criteria andExchangeNumNotIn(List<Integer> values) {
            addCriterion("a.exchange_num not in", values, "exchangeNum");
            return (Criteria) this;
        }

        public Criteria andExchangeNumBetween(Integer value1, Integer value2) {
            addCriterion("a.exchange_num between", value1, value2, "exchangeNum");
            return (Criteria) this;
        }

        public Criteria andExchangeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("a.exchange_num not between", value1, value2, "exchangeNum");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumIsNull() {
            addCriterion("a.exchange_after_num is null");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumIsNotNull() {
            addCriterion("a.exchange_after_num is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumEqualTo(Integer value) {
            addCriterion("a.exchange_after_num =", value, "exchangeAfterNum");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumNotEqualTo(Integer value) {
            addCriterion("a.exchange_after_num <>", value, "exchangeAfterNum");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumGreaterThan(Integer value) {
            addCriterion("a.exchange_after_num >", value, "exchangeAfterNum");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.exchange_after_num >=", value, "exchangeAfterNum");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumLessThan(Integer value) {
            addCriterion("a.exchange_after_num <", value, "exchangeAfterNum");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumLessThanOrEqualTo(Integer value) {
            addCriterion("a.exchange_after_num <=", value, "exchangeAfterNum");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumIn(List<Integer> values) {
            addCriterion("a.exchange_after_num in", values, "exchangeAfterNum");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumNotIn(List<Integer> values) {
            addCriterion("a.exchange_after_num not in", values, "exchangeAfterNum");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumBetween(Integer value1, Integer value2) {
            addCriterion("a.exchange_after_num between", value1, value2, "exchangeAfterNum");
            return (Criteria) this;
        }

        public Criteria andExchangeAfterNumNotBetween(Integer value1, Integer value2) {
            addCriterion("a.exchange_after_num not between", value1, value2, "exchangeAfterNum");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("a.time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("a.time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(String value) {
            addCriterion("a.time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(String value) {
            addCriterion("a.time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(String value) {
            addCriterion("a.time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(String value) {
            addCriterion("a.time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(String value) {
            addCriterion("a.time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(String value) {
            addCriterion("a.time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLike(String value) {
            addCriterion("a.time like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotLike(String value) {
            addCriterion("a.time not like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<String> values) {
            addCriterion("a.time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<String> values) {
            addCriterion("a.time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(String value1, String value2) {
            addCriterion("a.time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(String value1, String value2) {
            addCriterion("a.time not between", value1, value2, "time");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yy_activity_exchange_gold
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
     * This class corresponds to the database table yy_activity_exchange_gold
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