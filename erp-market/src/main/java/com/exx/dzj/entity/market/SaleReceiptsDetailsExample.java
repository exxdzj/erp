package com.exx.dzj.entity.market;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleReceiptsDetailsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SaleReceiptsDetailsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSaleCodeIsNull() {
            addCriterion("sale_code is null");
            return (Criteria) this;
        }

        public Criteria andSaleCodeIsNotNull() {
            addCriterion("sale_code is not null");
            return (Criteria) this;
        }

        public Criteria andSaleCodeEqualTo(String value) {
            addCriterion("sale_code =", value, "saleCode");
            return (Criteria) this;
        }

        public Criteria andSaleCodeNotEqualTo(String value) {
            addCriterion("sale_code <>", value, "saleCode");
            return (Criteria) this;
        }

        public Criteria andSaleCodeGreaterThan(String value) {
            addCriterion("sale_code >", value, "saleCode");
            return (Criteria) this;
        }

        public Criteria andSaleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sale_code >=", value, "saleCode");
            return (Criteria) this;
        }

        public Criteria andSaleCodeLessThan(String value) {
            addCriterion("sale_code <", value, "saleCode");
            return (Criteria) this;
        }

        public Criteria andSaleCodeLessThanOrEqualTo(String value) {
            addCriterion("sale_code <=", value, "saleCode");
            return (Criteria) this;
        }

        public Criteria andSaleCodeLike(String value) {
            addCriterion("sale_code like", value, "saleCode");
            return (Criteria) this;
        }

        public Criteria andSaleCodeNotLike(String value) {
            addCriterion("sale_code not like", value, "saleCode");
            return (Criteria) this;
        }

        public Criteria andSaleCodeIn(List<String> values) {
            addCriterion("sale_code in", values, "saleCode");
            return (Criteria) this;
        }

        public Criteria andSaleCodeNotIn(List<String> values) {
            addCriterion("sale_code not in", values, "saleCode");
            return (Criteria) this;
        }

        public Criteria andSaleCodeBetween(String value1, String value2) {
            addCriterion("sale_code between", value1, value2, "saleCode");
            return (Criteria) this;
        }

        public Criteria andSaleCodeNotBetween(String value1, String value2) {
            addCriterion("sale_code not between", value1, value2, "saleCode");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountIsNull() {
            addCriterion("collection_account is null");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountIsNotNull() {
            addCriterion("collection_account is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountEqualTo(String value) {
            addCriterion("collection_account =", value, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountNotEqualTo(String value) {
            addCriterion("collection_account <>", value, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountGreaterThan(String value) {
            addCriterion("collection_account >", value, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountGreaterThanOrEqualTo(String value) {
            addCriterion("collection_account >=", value, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountLessThan(String value) {
            addCriterion("collection_account <", value, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountLessThanOrEqualTo(String value) {
            addCriterion("collection_account <=", value, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountLike(String value) {
            addCriterion("collection_account like", value, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountNotLike(String value) {
            addCriterion("collection_account not like", value, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountIn(List<String> values) {
            addCriterion("collection_account in", values, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountNotIn(List<String> values) {
            addCriterion("collection_account not in", values, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountBetween(String value1, String value2) {
            addCriterion("collection_account between", value1, value2, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectionAccountNotBetween(String value1, String value2) {
            addCriterion("collection_account not between", value1, value2, "collectionAccount");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountIsNull() {
            addCriterion("collected_amount is null");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountIsNotNull() {
            addCriterion("collected_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountEqualTo(BigDecimal value) {
            addCriterion("collected_amount =", value, "collectedAmount");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountNotEqualTo(BigDecimal value) {
            addCriterion("collected_amount <>", value, "collectedAmount");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountGreaterThan(BigDecimal value) {
            addCriterion("collected_amount >", value, "collectedAmount");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("collected_amount >=", value, "collectedAmount");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountLessThan(BigDecimal value) {
            addCriterion("collected_amount <", value, "collectedAmount");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("collected_amount <=", value, "collectedAmount");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountIn(List<BigDecimal> values) {
            addCriterion("collected_amount in", values, "collectedAmount");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountNotIn(List<BigDecimal> values) {
            addCriterion("collected_amount not in", values, "collectedAmount");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collected_amount between", value1, value2, "collectedAmount");
            return (Criteria) this;
        }

        public Criteria andCollectedAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collected_amount not between", value1, value2, "collectedAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNull() {
            addCriterion("payment_method is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNotNull() {
            addCriterion("payment_method is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodEqualTo(String value) {
            addCriterion("payment_method =", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotEqualTo(String value) {
            addCriterion("payment_method <>", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThan(String value) {
            addCriterion("payment_method >", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThanOrEqualTo(String value) {
            addCriterion("payment_method >=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThan(String value) {
            addCriterion("payment_method <", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThanOrEqualTo(String value) {
            addCriterion("payment_method <=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLike(String value) {
            addCriterion("payment_method like", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotLike(String value) {
            addCriterion("payment_method not like", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIn(List<String> values) {
            addCriterion("payment_method in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotIn(List<String> values) {
            addCriterion("payment_method not in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodBetween(String value1, String value2) {
            addCriterion("payment_method between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotBetween(String value1, String value2) {
            addCriterion("payment_method not between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andRefNoIsNull() {
            addCriterion("ref_no is null");
            return (Criteria) this;
        }

        public Criteria andRefNoIsNotNull() {
            addCriterion("ref_no is not null");
            return (Criteria) this;
        }

        public Criteria andRefNoEqualTo(String value) {
            addCriterion("ref_no =", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotEqualTo(String value) {
            addCriterion("ref_no <>", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoGreaterThan(String value) {
            addCriterion("ref_no >", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoGreaterThanOrEqualTo(String value) {
            addCriterion("ref_no >=", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoLessThan(String value) {
            addCriterion("ref_no <", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoLessThanOrEqualTo(String value) {
            addCriterion("ref_no <=", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoLike(String value) {
            addCriterion("ref_no like", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotLike(String value) {
            addCriterion("ref_no not like", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoIn(List<String> values) {
            addCriterion("ref_no in", values, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotIn(List<String> values) {
            addCriterion("ref_no not in", values, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoBetween(String value1, String value2) {
            addCriterion("ref_no between", value1, value2, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotBetween(String value1, String value2) {
            addCriterion("ref_no not between", value1, value2, "refNo");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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