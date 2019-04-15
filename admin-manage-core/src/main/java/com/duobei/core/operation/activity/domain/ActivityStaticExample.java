package com.duobei.core.operation.activity.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityStaticExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public ActivityStaticExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
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
     * This method corresponds to the database table yy_activity_static
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
     * This method corresponds to the database table yy_activity_static
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_static
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
     * This class corresponds to the database table yy_activity_static
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

        public Criteria andBtnTypeIsNull() {
            addCriterion("a.btn_type is null");
            return (Criteria) this;
        }

        public Criteria andBtnTypeIsNotNull() {
            addCriterion("a.btn_type is not null");
            return (Criteria) this;
        }

        public Criteria andBtnTypeEqualTo(String value) {
            addCriterion("a.btn_type =", value, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTypeNotEqualTo(String value) {
            addCriterion("a.btn_type <>", value, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTypeGreaterThan(String value) {
            addCriterion("a.btn_type >", value, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTypeGreaterThanOrEqualTo(String value) {
            addCriterion("a.btn_type >=", value, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTypeLessThan(String value) {
            addCriterion("a.btn_type <", value, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTypeLessThanOrEqualTo(String value) {
            addCriterion("a.btn_type <=", value, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTypeLike(String value) {
            addCriterion("a.btn_type like", value, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTypeNotLike(String value) {
            addCriterion("a.btn_type not like", value, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTypeIn(List<String> values) {
            addCriterion("a.btn_type in", values, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTypeNotIn(List<String> values) {
            addCriterion("a.btn_type not in", values, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTypeBetween(String value1, String value2) {
            addCriterion("a.btn_type between", value1, value2, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTypeNotBetween(String value1, String value2) {
            addCriterion("a.btn_type not between", value1, value2, "btnType");
            return (Criteria) this;
        }

        public Criteria andBtnTextIsNull() {
            addCriterion("a.btn_text is null");
            return (Criteria) this;
        }

        public Criteria andBtnTextIsNotNull() {
            addCriterion("a.btn_text is not null");
            return (Criteria) this;
        }

        public Criteria andBtnTextEqualTo(String value) {
            addCriterion("a.btn_text =", value, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnTextNotEqualTo(String value) {
            addCriterion("a.btn_text <>", value, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnTextGreaterThan(String value) {
            addCriterion("a.btn_text >", value, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnTextGreaterThanOrEqualTo(String value) {
            addCriterion("a.btn_text >=", value, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnTextLessThan(String value) {
            addCriterion("a.btn_text <", value, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnTextLessThanOrEqualTo(String value) {
            addCriterion("a.btn_text <=", value, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnTextLike(String value) {
            addCriterion("a.btn_text like", value, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnTextNotLike(String value) {
            addCriterion("a.btn_text not like", value, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnTextIn(List<String> values) {
            addCriterion("a.btn_text in", values, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnTextNotIn(List<String> values) {
            addCriterion("a.btn_text not in", values, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnTextBetween(String value1, String value2) {
            addCriterion("a.btn_text between", value1, value2, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnTextNotBetween(String value1, String value2) {
            addCriterion("a.btn_text not between", value1, value2, "btnText");
            return (Criteria) this;
        }

        public Criteria andBtnColourIsNull() {
            addCriterion("a.btn_colour is null");
            return (Criteria) this;
        }

        public Criteria andBtnColourIsNotNull() {
            addCriterion("a.btn_colour is not null");
            return (Criteria) this;
        }

        public Criteria andBtnColourEqualTo(String value) {
            addCriterion("a.btn_colour =", value, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnColourNotEqualTo(String value) {
            addCriterion("a.btn_colour <>", value, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnColourGreaterThan(String value) {
            addCriterion("a.btn_colour >", value, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnColourGreaterThanOrEqualTo(String value) {
            addCriterion("a.btn_colour >=", value, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnColourLessThan(String value) {
            addCriterion("a.btn_colour <", value, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnColourLessThanOrEqualTo(String value) {
            addCriterion("a.btn_colour <=", value, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnColourLike(String value) {
            addCriterion("a.btn_colour like", value, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnColourNotLike(String value) {
            addCriterion("a.btn_colour not like", value, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnColourIn(List<String> values) {
            addCriterion("a.btn_colour in", values, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnColourNotIn(List<String> values) {
            addCriterion("a.btn_colour not in", values, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnColourBetween(String value1, String value2) {
            addCriterion("a.btn_colour between", value1, value2, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnColourNotBetween(String value1, String value2) {
            addCriterion("a.btn_colour not between", value1, value2, "btnColour");
            return (Criteria) this;
        }

        public Criteria andBtnImgIsNull() {
            addCriterion("a.btn_img is null");
            return (Criteria) this;
        }

        public Criteria andBtnImgIsNotNull() {
            addCriterion("a.btn_img is not null");
            return (Criteria) this;
        }

        public Criteria andBtnImgEqualTo(String value) {
            addCriterion("a.btn_img =", value, "btnImg");
            return (Criteria) this;
        }

        public Criteria andBtnImgNotEqualTo(String value) {
            addCriterion("a.btn_img <>", value, "btnImg");
            return (Criteria) this;
        }

        public Criteria andBtnImgGreaterThan(String value) {
            addCriterion("a.btn_img >", value, "btnImg");
            return (Criteria) this;
        }

        public Criteria andBtnImgGreaterThanOrEqualTo(String value) {
            addCriterion("a.btn_img >=", value, "btnImg");
            return (Criteria) this;
        }

        public Criteria andBtnImgLessThan(String value) {
            addCriterion("a.btn_img <", value, "btnImg");
            return (Criteria) this;
        }

        public Criteria andBtnImgLessThanOrEqualTo(String value) {
            addCriterion("a.btn_img <=", value, "btnImg");
            return (Criteria) this;
        }

        public Criteria andBtnImgLike(String value) {
            addCriterion("a.btn_img like", value, "btnImg");
            return (Criteria) this;
        }

        public Criteria andBtnImgNotLike(String value) {
            addCriterion("a.btn_img not like", value, "btnImg");
            return (Criteria) this;
        }

        public Criteria andBtnImgIn(List<String> values) {
            addCriterion("a.btn_img in", values, "btnImg");
            return (Criteria) this;
        }

        public Criteria andBtnImgNotIn(List<String> values) {
            addCriterion("a.btn_img not in", values, "btnImg");
            return (Criteria) this;
        }

        public Criteria andBtnImgBetween(String value1, String value2) {
            addCriterion("a.btn_img between", value1, value2, "btnImg");
            return (Criteria) this;
        }

        public Criteria andBtnImgNotBetween(String value1, String value2) {
            addCriterion("a.btn_img not between", value1, value2, "btnImg");
            return (Criteria) this;
        }

        public Criteria andJumpTypeIsNull() {
            addCriterion("a.jump_type is null");
            return (Criteria) this;
        }

        public Criteria andJumpTypeIsNotNull() {
            addCriterion("a.jump_type is not null");
            return (Criteria) this;
        }

        public Criteria andJumpTypeEqualTo(String value) {
            addCriterion("a.jump_type =", value, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpTypeNotEqualTo(String value) {
            addCriterion("a.jump_type <>", value, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpTypeGreaterThan(String value) {
            addCriterion("a.jump_type >", value, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpTypeGreaterThanOrEqualTo(String value) {
            addCriterion("a.jump_type >=", value, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpTypeLessThan(String value) {
            addCriterion("a.jump_type <", value, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpTypeLessThanOrEqualTo(String value) {
            addCriterion("a.jump_type <=", value, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpTypeLike(String value) {
            addCriterion("a.jump_type like", value, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpTypeNotLike(String value) {
            addCriterion("a.jump_type not like", value, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpTypeIn(List<String> values) {
            addCriterion("a.jump_type in", values, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpTypeNotIn(List<String> values) {
            addCriterion("a.jump_type not in", values, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpTypeBetween(String value1, String value2) {
            addCriterion("a.jump_type between", value1, value2, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpTypeNotBetween(String value1, String value2) {
            addCriterion("a.jump_type not between", value1, value2, "jumpType");
            return (Criteria) this;
        }

        public Criteria andJumpLinkIsNull() {
            addCriterion("a.jump_link is null");
            return (Criteria) this;
        }

        public Criteria andJumpLinkIsNotNull() {
            addCriterion("a.jump_link is not null");
            return (Criteria) this;
        }

        public Criteria andJumpLinkEqualTo(String value) {
            addCriterion("a.jump_link =", value, "jumpLink");
            return (Criteria) this;
        }

        public Criteria andJumpLinkNotEqualTo(String value) {
            addCriterion("a.jump_link <>", value, "jumpLink");
            return (Criteria) this;
        }

        public Criteria andJumpLinkGreaterThan(String value) {
            addCriterion("a.jump_link >", value, "jumpLink");
            return (Criteria) this;
        }

        public Criteria andJumpLinkGreaterThanOrEqualTo(String value) {
            addCriterion("a.jump_link >=", value, "jumpLink");
            return (Criteria) this;
        }

        public Criteria andJumpLinkLessThan(String value) {
            addCriterion("a.jump_link <", value, "jumpLink");
            return (Criteria) this;
        }

        public Criteria andJumpLinkLessThanOrEqualTo(String value) {
            addCriterion("a.jump_link <=", value, "jumpLink");
            return (Criteria) this;
        }

        public Criteria andJumpLinkLike(String value) {
            addCriterion("a.jump_link like", value, "jumpLink");
            return (Criteria) this;
        }

        public Criteria andJumpLinkNotLike(String value) {
            addCriterion("a.jump_link not like", value, "jumpLink");
            return (Criteria) this;
        }

        public Criteria andJumpLinkIn(List<String> values) {
            addCriterion("a.jump_link in", values, "jumpLink");
            return (Criteria) this;
        }

        public Criteria andJumpLinkNotIn(List<String> values) {
            addCriterion("a.jump_link not in", values, "jumpLink");
            return (Criteria) this;
        }

        public Criteria andJumpLinkBetween(String value1, String value2) {
            addCriterion("a.jump_link between", value1, value2, "jumpLink");
            return (Criteria) this;
        }

        public Criteria andJumpLinkNotBetween(String value1, String value2) {
            addCriterion("a.jump_link not between", value1, value2, "jumpLink");
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

        public Criteria andRuleEnableIsNull() {
            addCriterion("a.rule_enable is null");
            return (Criteria) this;
        }

        public Criteria andRuleEnableIsNotNull() {
            addCriterion("a.rule_enable is not null");
            return (Criteria) this;
        }

        public Criteria andRuleEnableEqualTo(Byte value) {
            addCriterion("a.rule_enable =", value, "ruleEnable");
            return (Criteria) this;
        }

        public Criteria andRuleEnableNotEqualTo(Byte value) {
            addCriterion("a.rule_enable <>", value, "ruleEnable");
            return (Criteria) this;
        }

        public Criteria andRuleEnableGreaterThan(Byte value) {
            addCriterion("a.rule_enable >", value, "ruleEnable");
            return (Criteria) this;
        }

        public Criteria andRuleEnableGreaterThanOrEqualTo(Byte value) {
            addCriterion("a.rule_enable >=", value, "ruleEnable");
            return (Criteria) this;
        }

        public Criteria andRuleEnableLessThan(Byte value) {
            addCriterion("a.rule_enable <", value, "ruleEnable");
            return (Criteria) this;
        }

        public Criteria andRuleEnableLessThanOrEqualTo(Byte value) {
            addCriterion("a.rule_enable <=", value, "ruleEnable");
            return (Criteria) this;
        }

        public Criteria andRuleEnableIn(List<Byte> values) {
            addCriterion("a.rule_enable in", values, "ruleEnable");
            return (Criteria) this;
        }

        public Criteria andRuleEnableNotIn(List<Byte> values) {
            addCriterion("a.rule_enable not in", values, "ruleEnable");
            return (Criteria) this;
        }

        public Criteria andRuleEnableBetween(Byte value1, Byte value2) {
            addCriterion("a.rule_enable between", value1, value2, "ruleEnable");
            return (Criteria) this;
        }

        public Criteria andRuleEnableNotBetween(Byte value1, Byte value2) {
            addCriterion("a.rule_enable not between", value1, value2, "ruleEnable");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yy_activity_static
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
     * This class corresponds to the database table yy_activity_static
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