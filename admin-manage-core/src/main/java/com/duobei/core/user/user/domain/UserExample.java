package com.duobei.core.user.user.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    public UserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
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
     * This method corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
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
            addCriterion("user.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("user.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("user.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("user.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("user.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("user.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("user.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("user.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("user.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("user.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("user.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("user.product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("user.product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("user.product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("user.product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("user.product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user.product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("user.product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("user.product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("user.product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("user.product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("user.product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user.product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNull() {
            addCriterion("user.app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("user.app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(Integer value) {
            addCriterion("user.app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(Integer value) {
            addCriterion("user.app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(Integer value) {
            addCriterion("user.app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user.app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(Integer value) {
            addCriterion("user.app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(Integer value) {
            addCriterion("user.app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<Integer> values) {
            addCriterion("user.app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<Integer> values) {
            addCriterion("user.app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(Integer value1, Integer value2) {
            addCriterion("user.app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user.app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdIsNull() {
            addCriterion("user.global_user_id is null");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdIsNotNull() {
            addCriterion("user.global_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdEqualTo(String value) {
            addCriterion("user.global_user_id =", value, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdNotEqualTo(String value) {
            addCriterion("user.global_user_id <>", value, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdGreaterThan(String value) {
            addCriterion("user.global_user_id >", value, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user.global_user_id >=", value, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdLessThan(String value) {
            addCriterion("user.global_user_id <", value, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdLessThanOrEqualTo(String value) {
            addCriterion("user.global_user_id <=", value, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdLike(String value) {
            addCriterion("user.global_user_id like", value, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdNotLike(String value) {
            addCriterion("user.global_user_id not like", value, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdIn(List<String> values) {
            addCriterion("user.global_user_id in", values, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdNotIn(List<String> values) {
            addCriterion("user.global_user_id not in", values, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdBetween(String value1, String value2) {
            addCriterion("user.global_user_id between", value1, value2, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andGlobalUserIdNotBetween(String value1, String value2) {
            addCriterion("user.global_user_id not between", value1, value2, "globalUserId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user.user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user.user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user.user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user.user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user.user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user.user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user.user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user.user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user.user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user.user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user.user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user.user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user.user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user.user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5IsNull() {
            addCriterion("user.user_name_md5 is null");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5IsNotNull() {
            addCriterion("user.user_name_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5EqualTo(String value) {
            addCriterion("user.user_name_md5 =", value, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5NotEqualTo(String value) {
            addCriterion("user.user_name_md5 <>", value, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5GreaterThan(String value) {
            addCriterion("user.user_name_md5 >", value, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5GreaterThanOrEqualTo(String value) {
            addCriterion("user.user_name_md5 >=", value, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5LessThan(String value) {
            addCriterion("user.user_name_md5 <", value, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5LessThanOrEqualTo(String value) {
            addCriterion("user.user_name_md5 <=", value, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5Like(String value) {
            addCriterion("user.user_name_md5 like", value, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5NotLike(String value) {
            addCriterion("user.user_name_md5 not like", value, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5In(List<String> values) {
            addCriterion("user.user_name_md5 in", values, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5NotIn(List<String> values) {
            addCriterion("user.user_name_md5 not in", values, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5Between(String value1, String value2) {
            addCriterion("user.user_name_md5 between", value1, value2, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameMd5NotBetween(String value1, String value2) {
            addCriterion("user.user_name_md5 not between", value1, value2, "userNameMd5");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptIsNull() {
            addCriterion("user.user_name_encrypt is null");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptIsNotNull() {
            addCriterion("user.user_name_encrypt is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptEqualTo(String value) {
            addCriterion("user.user_name_encrypt =", value, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptNotEqualTo(String value) {
            addCriterion("user.user_name_encrypt <>", value, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptGreaterThan(String value) {
            addCriterion("user.user_name_encrypt >", value, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptGreaterThanOrEqualTo(String value) {
            addCriterion("user.user_name_encrypt >=", value, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptLessThan(String value) {
            addCriterion("user.user_name_encrypt <", value, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptLessThanOrEqualTo(String value) {
            addCriterion("user.user_name_encrypt <=", value, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptLike(String value) {
            addCriterion("user.user_name_encrypt like", value, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptNotLike(String value) {
            addCriterion("user.user_name_encrypt not like", value, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptIn(List<String> values) {
            addCriterion("user.user_name_encrypt in", values, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptNotIn(List<String> values) {
            addCriterion("user.user_name_encrypt not in", values, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptBetween(String value1, String value2) {
            addCriterion("user.user_name_encrypt between", value1, value2, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andUserNameEncryptNotBetween(String value1, String value2) {
            addCriterion("user.user_name_encrypt not between", value1, value2, "userNameEncrypt");
            return (Criteria) this;
        }

        public Criteria andPwdIsNull() {
            addCriterion("user.pwd is null");
            return (Criteria) this;
        }

        public Criteria andPwdIsNotNull() {
            addCriterion("user.pwd is not null");
            return (Criteria) this;
        }

        public Criteria andPwdEqualTo(String value) {
            addCriterion("user.pwd =", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualTo(String value) {
            addCriterion("user.pwd <>", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThan(String value) {
            addCriterion("user.pwd >", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("user.pwd >=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThan(String value) {
            addCriterion("user.pwd <", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("user.pwd <=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLike(String value) {
            addCriterion("user.pwd like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotLike(String value) {
            addCriterion("user.pwd not like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdIn(List<String> values) {
            addCriterion("user.pwd in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotIn(List<String> values) {
            addCriterion("user.pwd not in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdBetween(String value1, String value2) {
            addCriterion("user.pwd between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotBetween(String value1, String value2) {
            addCriterion("user.pwd not between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("user.salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("user.salt is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("user.salt =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("user.salt <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("user.salt >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("user.salt >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("user.salt <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("user.salt <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("user.salt like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("user.salt not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("user.salt in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("user.salt not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("user.salt between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("user.salt not between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNull() {
            addCriterion("user.channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("user.channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(Integer value) {
            addCriterion("user.channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(Integer value) {
            addCriterion("user.channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(Integer value) {
            addCriterion("user.channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user.channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(Integer value) {
            addCriterion("user.channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(Integer value) {
            addCriterion("user.channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<Integer> values) {
            addCriterion("user.channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<Integer> values) {
            addCriterion("user.channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(Integer value1, Integer value2) {
            addCriterion("user.channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user.channel_id not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("user.add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("user.add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("user.add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("user.add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("user.add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user.add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("user.add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("user.add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("user.add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("user.add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("user.add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("user.add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("user.modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("user.modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("user.modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("user.modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("user.modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user.modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("user.modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("user.modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("user.modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("user.modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("user.modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("user.modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("user.is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("user.is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Long value) {
            addCriterion("user.is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Long value) {
            addCriterion("user.is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Long value) {
            addCriterion("user.is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Long value) {
            addCriterion("user.is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Long value) {
            addCriterion("user.is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Long value) {
            addCriterion("user.is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Long> values) {
            addCriterion("user.is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Long> values) {
            addCriterion("user.is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Long value1, Long value2) {
            addCriterion("user.is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Long value1, Long value2) {
            addCriterion("user.is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table pgy_user
     *
     * @mbg.generated do_not_delete_during_merge Wed Apr 17 09:49:05 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table pgy_user
     *
     * @mbg.generated Wed Apr 17 09:49:05 CST 2019
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