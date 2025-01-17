package com.duobei.core.manage.auth.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrganRuleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    public OrganRuleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
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
     * This method corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
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

        public Criteria andOrganRuleIdIsNull() {
            addCriterion("orr.organ_rule_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganRuleIdIsNotNull() {
            addCriterion("orr.organ_rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganRuleIdEqualTo(Integer value) {
            addCriterion("orr.organ_rule_id =", value, "organRuleId");
            return (Criteria) this;
        }

        public Criteria andOrganRuleIdNotEqualTo(Integer value) {
            addCriterion("orr.organ_rule_id <>", value, "organRuleId");
            return (Criteria) this;
        }

        public Criteria andOrganRuleIdGreaterThan(Integer value) {
            addCriterion("orr.organ_rule_id >", value, "organRuleId");
            return (Criteria) this;
        }

        public Criteria andOrganRuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("orr.organ_rule_id >=", value, "organRuleId");
            return (Criteria) this;
        }

        public Criteria andOrganRuleIdLessThan(Integer value) {
            addCriterion("orr.organ_rule_id <", value, "organRuleId");
            return (Criteria) this;
        }

        public Criteria andOrganRuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("orr.organ_rule_id <=", value, "organRuleId");
            return (Criteria) this;
        }

        public Criteria andOrganRuleIdIn(List<Integer> values) {
            addCriterion("orr.organ_rule_id in", values, "organRuleId");
            return (Criteria) this;
        }

        public Criteria andOrganRuleIdNotIn(List<Integer> values) {
            addCriterion("orr.organ_rule_id not in", values, "organRuleId");
            return (Criteria) this;
        }

        public Criteria andOrganRuleIdBetween(Integer value1, Integer value2) {
            addCriterion("orr.organ_rule_id between", value1, value2, "organRuleId");
            return (Criteria) this;
        }

        public Criteria andOrganRuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("orr.organ_rule_id not between", value1, value2, "organRuleId");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdIsNull() {
            addCriterion("orr.sup_organ_type_id is null");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdIsNotNull() {
            addCriterion("orr.sup_organ_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdEqualTo(Integer value) {
            addCriterion("orr.sup_organ_type_id =", value, "supOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdNotEqualTo(Integer value) {
            addCriterion("orr.sup_organ_type_id <>", value, "supOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdGreaterThan(Integer value) {
            addCriterion("orr.sup_organ_type_id >", value, "supOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("orr.sup_organ_type_id >=", value, "supOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdLessThan(Integer value) {
            addCriterion("orr.sup_organ_type_id <", value, "supOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("orr.sup_organ_type_id <=", value, "supOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdIn(List<Integer> values) {
            addCriterion("orr.sup_organ_type_id in", values, "supOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdNotIn(List<Integer> values) {
            addCriterion("orr.sup_organ_type_id not in", values, "supOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("orr.sup_organ_type_id between", value1, value2, "supOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSupOrganTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("orr.sup_organ_type_id not between", value1, value2, "supOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdIsNull() {
            addCriterion("orr.sub_organ_type_id is null");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdIsNotNull() {
            addCriterion("orr.sub_organ_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdEqualTo(Integer value) {
            addCriterion("orr.sub_organ_type_id =", value, "subOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdNotEqualTo(Integer value) {
            addCriterion("orr.sub_organ_type_id <>", value, "subOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdGreaterThan(Integer value) {
            addCriterion("orr.sub_organ_type_id >", value, "subOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("orr.sub_organ_type_id >=", value, "subOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdLessThan(Integer value) {
            addCriterion("orr.sub_organ_type_id <", value, "subOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("orr.sub_organ_type_id <=", value, "subOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdIn(List<Integer> values) {
            addCriterion("orr.sub_organ_type_id in", values, "subOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdNotIn(List<Integer> values) {
            addCriterion("orr.sub_organ_type_id not in", values, "subOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("orr.sub_organ_type_id between", value1, value2, "subOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andSubOrganTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("orr.sub_organ_type_id not between", value1, value2, "subOrganTypeId");
            return (Criteria) this;
        }

        public Criteria andIsSystemIsNull() {
            addCriterion("orr.is_system is null");
            return (Criteria) this;
        }

        public Criteria andIsSystemIsNotNull() {
            addCriterion("orr.is_system is not null");
            return (Criteria) this;
        }

        public Criteria andIsSystemEqualTo(Boolean value) {
            addCriterion("orr.is_system =", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemNotEqualTo(Boolean value) {
            addCriterion("orr.is_system <>", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemGreaterThan(Boolean value) {
            addCriterion("orr.is_system >", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemGreaterThanOrEqualTo(Boolean value) {
            addCriterion("orr.is_system >=", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemLessThan(Boolean value) {
            addCriterion("orr.is_system <", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemLessThanOrEqualTo(Boolean value) {
            addCriterion("orr.is_system <=", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemIn(List<Boolean> values) {
            addCriterion("orr.is_system in", values, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemNotIn(List<Boolean> values) {
            addCriterion("orr.is_system not in", values, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemBetween(Boolean value1, Boolean value2) {
            addCriterion("orr.is_system between", value1, value2, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemNotBetween(Boolean value1, Boolean value2) {
            addCriterion("orr.is_system not between", value1, value2, "isSystem");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("orr.remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("orr.remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("orr.remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("orr.remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("orr.remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("orr.remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("orr.remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("orr.remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("orr.remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("orr.remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("orr.remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("orr.remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("orr.remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("orr.remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("orr.add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("orr.add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("orr.add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("orr.add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("orr.add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("orr.add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("orr.add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("orr.add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("orr.add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("orr.add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("orr.add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("orr.add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("orr.modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("orr.modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("orr.modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("orr.modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("orr.modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("orr.modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("orr.modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("orr.modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("orr.modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("orr.modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("orr.modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("orr.modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdIsNull() {
            addCriterion("orr.add_operator_id is null");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdIsNotNull() {
            addCriterion("orr.add_operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdEqualTo(Integer value) {
            addCriterion("orr.add_operator_id =", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdNotEqualTo(Integer value) {
            addCriterion("orr.add_operator_id <>", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdGreaterThan(Integer value) {
            addCriterion("orr.add_operator_id >", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("orr.add_operator_id >=", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdLessThan(Integer value) {
            addCriterion("orr.add_operator_id <", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("orr.add_operator_id <=", value, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdIn(List<Integer> values) {
            addCriterion("orr.add_operator_id in", values, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdNotIn(List<Integer> values) {
            addCriterion("orr.add_operator_id not in", values, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("orr.add_operator_id between", value1, value2, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("orr.add_operator_id not between", value1, value2, "addOperatorId");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameIsNull() {
            addCriterion("orr.add_operator_name is null");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameIsNotNull() {
            addCriterion("orr.add_operator_name is not null");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameEqualTo(String value) {
            addCriterion("orr.add_operator_name =", value, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameNotEqualTo(String value) {
            addCriterion("orr.add_operator_name <>", value, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameGreaterThan(String value) {
            addCriterion("orr.add_operator_name >", value, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("orr.add_operator_name >=", value, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameLessThan(String value) {
            addCriterion("orr.add_operator_name <", value, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameLessThanOrEqualTo(String value) {
            addCriterion("orr.add_operator_name <=", value, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameLike(String value) {
            addCriterion("orr.add_operator_name like", value, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameNotLike(String value) {
            addCriterion("orr.add_operator_name not like", value, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameIn(List<String> values) {
            addCriterion("orr.add_operator_name in", values, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameNotIn(List<String> values) {
            addCriterion("orr.add_operator_name not in", values, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameBetween(String value1, String value2) {
            addCriterion("orr.add_operator_name between", value1, value2, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andAddOperatorNameNotBetween(String value1, String value2) {
            addCriterion("orr.add_operator_name not between", value1, value2, "addOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdIsNull() {
            addCriterion("orr.modify_operator_id is null");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdIsNotNull() {
            addCriterion("orr.modify_operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdEqualTo(Integer value) {
            addCriterion("orr.modify_operator_id =", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdNotEqualTo(Integer value) {
            addCriterion("orr.modify_operator_id <>", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdGreaterThan(Integer value) {
            addCriterion("orr.modify_operator_id >", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("orr.modify_operator_id >=", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdLessThan(Integer value) {
            addCriterion("orr.modify_operator_id <", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("orr.modify_operator_id <=", value, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdIn(List<Integer> values) {
            addCriterion("orr.modify_operator_id in", values, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdNotIn(List<Integer> values) {
            addCriterion("orr.modify_operator_id not in", values, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("orr.modify_operator_id between", value1, value2, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("orr.modify_operator_id not between", value1, value2, "modifyOperatorId");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameIsNull() {
            addCriterion("orr.modify_operator_name is null");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameIsNotNull() {
            addCriterion("orr.modify_operator_name is not null");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameEqualTo(String value) {
            addCriterion("orr.modify_operator_name =", value, "modifyOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameNotEqualTo(String value) {
            addCriterion("orr.modify_operator_name <>", value, "modifyOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameGreaterThan(String value) {
            addCriterion("orr.modify_operator_name >", value, "modifyOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("orr.modify_operator_name >=", value, "modifyOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameLessThan(String value) {
            addCriterion("orr.modify_operator_name <", value, "modifyOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameLessThanOrEqualTo(String value) {
            addCriterion("orr.modify_operator_name <=", value, "modifyOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameLike(String value) {
            addCriterion("orr.modify_operator_name like", value, "modifyOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameNotLike(String value) {
            addCriterion("orr.modify_operator_name not like", value, "modifyOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameIn(List<String> values) {
            addCriterion("orr.modify_operator_name in", values, "modifyOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameNotIn(List<String> values) {
            addCriterion("orr.modify_operator_name not in", values, "modifyOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameBetween(String value1, String value2) {
            addCriterion("orr.modify_operator_name between", value1, value2, "modifyOperatorName");
            return (Criteria) this;
        }

        public Criteria andModifyOperatorNameNotBetween(String value1, String value2) {
            addCriterion("orr.modify_operator_name not between", value1, value2, "modifyOperatorName");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table aa_organ_rule
     *
     * @mbg.generated do_not_delete_during_merge Thu Nov 22 23:59:47 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table aa_organ_rule
     *
     * @mbg.generated Thu Nov 22 23:59:47 CST 2018
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