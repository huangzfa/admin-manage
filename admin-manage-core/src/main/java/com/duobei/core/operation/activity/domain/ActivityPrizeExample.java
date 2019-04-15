package com.duobei.core.operation.activity.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityPrizeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public ActivityPrizeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_prize
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
     * This method corresponds to the database table yy_activity_prize
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
     * This method corresponds to the database table yy_activity_prize
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_activity_prize
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
     * This class corresponds to the database table yy_activity_prize
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

        public Criteria andPrizeNameIsNull() {
            addCriterion("a.prize_name is null");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIsNotNull() {
            addCriterion("a.prize_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeNameEqualTo(String value) {
            addCriterion("a.prize_name =", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotEqualTo(String value) {
            addCriterion("a.prize_name <>", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameGreaterThan(String value) {
            addCriterion("a.prize_name >", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameGreaterThanOrEqualTo(String value) {
            addCriterion("a.prize_name >=", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLessThan(String value) {
            addCriterion("a.prize_name <", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLessThanOrEqualTo(String value) {
            addCriterion("a.prize_name <=", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLike(String value) {
            addCriterion("a.prize_name like", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotLike(String value) {
            addCriterion("a.prize_name not like", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIn(List<String> values) {
            addCriterion("a.prize_name in", values, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotIn(List<String> values) {
            addCriterion("a.prize_name not in", values, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameBetween(String value1, String value2) {
            addCriterion("a.prize_name between", value1, value2, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotBetween(String value1, String value2) {
            addCriterion("a.prize_name not between", value1, value2, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeIsNull() {
            addCriterion("a.prize_type is null");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeIsNotNull() {
            addCriterion("a.prize_type is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeEqualTo(String value) {
            addCriterion("a.prize_type =", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeNotEqualTo(String value) {
            addCriterion("a.prize_type <>", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeGreaterThan(String value) {
            addCriterion("a.prize_type >", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("a.prize_type >=", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeLessThan(String value) {
            addCriterion("a.prize_type <", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeLessThanOrEqualTo(String value) {
            addCriterion("a.prize_type <=", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeLike(String value) {
            addCriterion("a.prize_type like", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeNotLike(String value) {
            addCriterion("a.prize_type not like", value, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeIn(List<String> values) {
            addCriterion("a.prize_type in", values, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeNotIn(List<String> values) {
            addCriterion("a.prize_type not in", values, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeBetween(String value1, String value2) {
            addCriterion("a.prize_type between", value1, value2, "prizeType");
            return (Criteria) this;
        }

        public Criteria andPrizeTypeNotBetween(String value1, String value2) {
            addCriterion("a.prize_type not between", value1, value2, "prizeType");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNull() {
            addCriterion("a.coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("a.coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(Long value) {
            addCriterion("a.coupon_id =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(Long value) {
            addCriterion("a.coupon_id <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(Long value) {
            addCriterion("a.coupon_id >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(Long value) {
            addCriterion("a.coupon_id >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(Long value) {
            addCriterion("a.coupon_id <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(Long value) {
            addCriterion("a.coupon_id <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<Long> values) {
            addCriterion("a.coupon_id in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<Long> values) {
            addCriterion("a.coupon_id not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(Long value1, Long value2) {
            addCriterion("a.coupon_id between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(Long value1, Long value2) {
            addCriterion("a.coupon_id not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("a.money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("a.money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("a.money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("a.money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("a.money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("a.money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("a.money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("a.money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("a.money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("a.money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("a.money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("a.money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("a.img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("a.img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("a.img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("a.img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("a.img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("a.img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("a.img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("a.img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("a.img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("a.img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("a.img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("a.img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("a.img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("a.img_url not between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andLinkIsNull() {
            addCriterion("a.link is null");
            return (Criteria) this;
        }

        public Criteria andLinkIsNotNull() {
            addCriterion("a.link is not null");
            return (Criteria) this;
        }

        public Criteria andLinkEqualTo(String value) {
            addCriterion("a.link =", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotEqualTo(String value) {
            addCriterion("a.link <>", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThan(String value) {
            addCriterion("a.link >", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThanOrEqualTo(String value) {
            addCriterion("a.link >=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThan(String value) {
            addCriterion("a.link <", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThanOrEqualTo(String value) {
            addCriterion("a.link <=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLike(String value) {
            addCriterion("a.link like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotLike(String value) {
            addCriterion("a.link not like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkIn(List<String> values) {
            addCriterion("a.link in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotIn(List<String> values) {
            addCriterion("a.link not in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkBetween(String value1, String value2) {
            addCriterion("a.link between", value1, value2, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotBetween(String value1, String value2) {
            addCriterion("a.link not between", value1, value2, "link");
            return (Criteria) this;
        }

        public Criteria andIsJumpIsNull() {
            addCriterion("a.is_jump is null");
            return (Criteria) this;
        }

        public Criteria andIsJumpIsNotNull() {
            addCriterion("a.is_jump is not null");
            return (Criteria) this;
        }

        public Criteria andIsJumpEqualTo(Integer value) {
            addCriterion("a.is_jump =", value, "isJump");
            return (Criteria) this;
        }

        public Criteria andIsJumpNotEqualTo(Integer value) {
            addCriterion("a.is_jump <>", value, "isJump");
            return (Criteria) this;
        }

        public Criteria andIsJumpGreaterThan(Integer value) {
            addCriterion("a.is_jump >", value, "isJump");
            return (Criteria) this;
        }

        public Criteria andIsJumpGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.is_jump >=", value, "isJump");
            return (Criteria) this;
        }

        public Criteria andIsJumpLessThan(Integer value) {
            addCriterion("a.is_jump <", value, "isJump");
            return (Criteria) this;
        }

        public Criteria andIsJumpLessThanOrEqualTo(Integer value) {
            addCriterion("a.is_jump <=", value, "isJump");
            return (Criteria) this;
        }

        public Criteria andIsJumpIn(List<Integer> values) {
            addCriterion("a.is_jump in", values, "isJump");
            return (Criteria) this;
        }

        public Criteria andIsJumpNotIn(List<Integer> values) {
            addCriterion("a.is_jump not in", values, "isJump");
            return (Criteria) this;
        }

        public Criteria andIsJumpBetween(Integer value1, Integer value2) {
            addCriterion("a.is_jump between", value1, value2, "isJump");
            return (Criteria) this;
        }

        public Criteria andIsJumpNotBetween(Integer value1, Integer value2) {
            addCriterion("a.is_jump not between", value1, value2, "isJump");
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
     * This class corresponds to the database table yy_activity_prize
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
     * This class corresponds to the database table yy_activity_prize
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