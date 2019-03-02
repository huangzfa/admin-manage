package com.duobei.core.operation.consume.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsumdebtGoodsClassExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    public ConsumdebtGoodsClassExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
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
     * This method corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
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
            addCriterion("cgc.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("cgc.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("cgc.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("cgc.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("cgc.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cgc.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("cgc.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("cgc.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("cgc.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("cgc.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("cgc.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cgc.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andClassCodeIsNull() {
            addCriterion("cgc.class_code is null");
            return (Criteria) this;
        }

        public Criteria andClassCodeIsNotNull() {
            addCriterion("cgc.class_code is not null");
            return (Criteria) this;
        }

        public Criteria andClassCodeEqualTo(String value) {
            addCriterion("cgc.class_code =", value, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassCodeNotEqualTo(String value) {
            addCriterion("cgc.class_code <>", value, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassCodeGreaterThan(String value) {
            addCriterion("cgc.class_code >", value, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cgc.class_code >=", value, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassCodeLessThan(String value) {
            addCriterion("cgc.class_code <", value, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassCodeLessThanOrEqualTo(String value) {
            addCriterion("cgc.class_code <=", value, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassCodeLike(String value) {
            addCriterion("cgc.class_code like", value, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassCodeNotLike(String value) {
            addCriterion("cgc.class_code not like", value, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassCodeIn(List<String> values) {
            addCriterion("cgc.class_code in", values, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassCodeNotIn(List<String> values) {
            addCriterion("cgc.class_code not in", values, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassCodeBetween(String value1, String value2) {
            addCriterion("cgc.class_code between", value1, value2, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassCodeNotBetween(String value1, String value2) {
            addCriterion("cgc.class_code not between", value1, value2, "classCode");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("cgc.class_name is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("cgc.class_name is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("cgc.class_name =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("cgc.class_name <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("cgc.class_name >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("cgc.class_name >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("cgc.class_name <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("cgc.class_name <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("cgc.class_name like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("cgc.class_name not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("cgc.class_name in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("cgc.class_name not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("cgc.class_name between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("cgc.class_name not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("cgc.add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("cgc.add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("cgc.add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("cgc.add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("cgc.add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cgc.add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("cgc.add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("cgc.add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("cgc.add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("cgc.add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("cgc.add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("cgc.add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("cgc.modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("cgc.modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("cgc.modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("cgc.modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("cgc.modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cgc.modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("cgc.modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("cgc.modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("cgc.modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("cgc.modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("cgc.modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("cgc.modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdIsNull() {
            addCriterion("cgc.add_operator_id is null");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdIsNotNull() {
            addCriterion("cgc.add_operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdEqualTo(Integer value) {
            addCriterion("cgc.add_operator_id =", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdNotEqualTo(Integer value) {
            addCriterion("cgc.add_operator_id <>", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdGreaterThan(Integer value) {
            addCriterion("cgc.add_operator_id >", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cgc.add_operator_id >=", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdLessThan(Integer value) {
            addCriterion("cgc.add_operator_id <", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("cgc.add_operator_id <=", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdIn(List<Integer> values) {
            addCriterion("cgc.add_operator_id in", values, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdNotIn(List<Integer> values) {
            addCriterion("cgc.add_operator_id not in", values, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("cgc.add_operator_id between", value1, value2, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cgc.add_operator_id not between", value1, value2, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdIsNull() {
            addCriterion("cgc.modify_operator_id is null");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdIsNotNull() {
            addCriterion("cgc.modify_operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdEqualTo(Integer value) {
            addCriterion("cgc.modify_operator_id =", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdNotEqualTo(Integer value) {
            addCriterion("cgc.modify_operator_id <>", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdGreaterThan(Integer value) {
            addCriterion("cgc.modify_operator_id >", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cgc.modify_operator_id >=", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdLessThan(Integer value) {
            addCriterion("cgc.modify_operator_id <", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("cgc.modify_operator_id <=", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdIn(List<Integer> values) {
            addCriterion("cgc.modify_operator_id in", values, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdNotIn(List<Integer> values) {
            addCriterion("cgc.modify_operator_id not in", values, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("cgc.modify_operator_id between", value1, value2, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cgc.modify_operator_id not between", value1, value2, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("cgc.is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("cgc.is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("cgc.is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("cgc.is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("cgc.is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("cgc.is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("cgc.is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("cgc.is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("cgc.is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("cgc.is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("cgc.is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("cgc.is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated do_not_delete_during_merge Sat Mar 02 14:39:00 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yy_consumdebt_goods_class
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
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