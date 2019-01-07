package com.exx.dzj.entity.accountatt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountAttributeBeanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountAttributeBeanExample() {
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

        public Criteria andCustCodeIsNull() {
            addCriterion("cust_code is null");
            return (Criteria) this;
        }

        public Criteria andCustCodeIsNotNull() {
            addCriterion("cust_code is not null");
            return (Criteria) this;
        }

        public Criteria andCustCodeEqualTo(String value) {
            addCriterion("cust_code =", value, "custCode");
            return (Criteria) this;
        }

        public Criteria andCustCodeNotEqualTo(String value) {
            addCriterion("cust_code <>", value, "custCode");
            return (Criteria) this;
        }

        public Criteria andCustCodeGreaterThan(String value) {
            addCriterion("cust_code >", value, "custCode");
            return (Criteria) this;
        }

        public Criteria andCustCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cust_code >=", value, "custCode");
            return (Criteria) this;
        }

        public Criteria andCustCodeLessThan(String value) {
            addCriterion("cust_code <", value, "custCode");
            return (Criteria) this;
        }

        public Criteria andCustCodeLessThanOrEqualTo(String value) {
            addCriterion("cust_code <=", value, "custCode");
            return (Criteria) this;
        }

        public Criteria andCustCodeLike(String value) {
            addCriterion("cust_code like", value, "custCode");
            return (Criteria) this;
        }

        public Criteria andCustCodeNotLike(String value) {
            addCriterion("cust_code not like", value, "custCode");
            return (Criteria) this;
        }

        public Criteria andCustCodeIn(List<String> values) {
            addCriterion("cust_code in", values, "custCode");
            return (Criteria) this;
        }

        public Criteria andCustCodeNotIn(List<String> values) {
            addCriterion("cust_code not in", values, "custCode");
            return (Criteria) this;
        }

        public Criteria andCustCodeBetween(String value1, String value2) {
            addCriterion("cust_code between", value1, value2, "custCode");
            return (Criteria) this;
        }

        public Criteria andCustCodeNotBetween(String value1, String value2) {
            addCriterion("cust_code not between", value1, value2, "custCode");
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

        public Criteria andDiscountRateIsNull() {
            addCriterion("discount_rate is null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNotNull() {
            addCriterion("discount_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateEqualTo(Double value) {
            addCriterion("discount_rate =", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotEqualTo(Double value) {
            addCriterion("discount_rate <>", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThan(Double value) {
            addCriterion("discount_rate >", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThanOrEqualTo(Double value) {
            addCriterion("discount_rate >=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThan(Double value) {
            addCriterion("discount_rate <", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThanOrEqualTo(Double value) {
            addCriterion("discount_rate <=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIn(List<Double> values) {
            addCriterion("discount_rate in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotIn(List<Double> values) {
            addCriterion("discount_rate not in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateBetween(Double value1, Double value2) {
            addCriterion("discount_rate between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotBetween(Double value1, Double value2) {
            addCriterion("discount_rate not between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodIsNull() {
            addCriterion("account_period is null");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodIsNotNull() {
            addCriterion("account_period is not null");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodEqualTo(Integer value) {
            addCriterion("account_period =", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodNotEqualTo(Integer value) {
            addCriterion("account_period <>", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodGreaterThan(Integer value) {
            addCriterion("account_period >", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_period >=", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodLessThan(Integer value) {
            addCriterion("account_period <", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("account_period <=", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodIn(List<Integer> values) {
            addCriterion("account_period in", values, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodNotIn(List<Integer> values) {
            addCriterion("account_period not in", values, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodBetween(Integer value1, Integer value2) {
            addCriterion("account_period between", value1, value2, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("account_period not between", value1, value2, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andLineCreditIsNull() {
            addCriterion("line_credit is null");
            return (Criteria) this;
        }

        public Criteria andLineCreditIsNotNull() {
            addCriterion("line_credit is not null");
            return (Criteria) this;
        }

        public Criteria andLineCreditEqualTo(Double value) {
            addCriterion("line_credit =", value, "lineCredit");
            return (Criteria) this;
        }

        public Criteria andLineCreditNotEqualTo(Double value) {
            addCriterion("line_credit <>", value, "lineCredit");
            return (Criteria) this;
        }

        public Criteria andLineCreditGreaterThan(Double value) {
            addCriterion("line_credit >", value, "lineCredit");
            return (Criteria) this;
        }

        public Criteria andLineCreditGreaterThanOrEqualTo(Double value) {
            addCriterion("line_credit >=", value, "lineCredit");
            return (Criteria) this;
        }

        public Criteria andLineCreditLessThan(Double value) {
            addCriterion("line_credit <", value, "lineCredit");
            return (Criteria) this;
        }

        public Criteria andLineCreditLessThanOrEqualTo(Double value) {
            addCriterion("line_credit <=", value, "lineCredit");
            return (Criteria) this;
        }

        public Criteria andLineCreditIn(List<Double> values) {
            addCriterion("line_credit in", values, "lineCredit");
            return (Criteria) this;
        }

        public Criteria andLineCreditNotIn(List<Double> values) {
            addCriterion("line_credit not in", values, "lineCredit");
            return (Criteria) this;
        }

        public Criteria andLineCreditBetween(Double value1, Double value2) {
            addCriterion("line_credit between", value1, value2, "lineCredit");
            return (Criteria) this;
        }

        public Criteria andLineCreditNotBetween(Double value1, Double value2) {
            addCriterion("line_credit not between", value1, value2, "lineCredit");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumIsNull() {
            addCriterion("pay_taxes_num is null");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumIsNotNull() {
            addCriterion("pay_taxes_num is not null");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumEqualTo(String value) {
            addCriterion("pay_taxes_num =", value, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumNotEqualTo(String value) {
            addCriterion("pay_taxes_num <>", value, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumGreaterThan(String value) {
            addCriterion("pay_taxes_num >", value, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumGreaterThanOrEqualTo(String value) {
            addCriterion("pay_taxes_num >=", value, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumLessThan(String value) {
            addCriterion("pay_taxes_num <", value, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumLessThanOrEqualTo(String value) {
            addCriterion("pay_taxes_num <=", value, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumLike(String value) {
            addCriterion("pay_taxes_num like", value, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumNotLike(String value) {
            addCriterion("pay_taxes_num not like", value, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumIn(List<String> values) {
            addCriterion("pay_taxes_num in", values, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumNotIn(List<String> values) {
            addCriterion("pay_taxes_num not in", values, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumBetween(String value1, String value2) {
            addCriterion("pay_taxes_num between", value1, value2, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andPayTaxesNumNotBetween(String value1, String value2) {
            addCriterion("pay_taxes_num not between", value1, value2, "payTaxesNum");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNull() {
            addCriterion("bill_type is null");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNotNull() {
            addCriterion("bill_type is not null");
            return (Criteria) this;
        }

        public Criteria andBillTypeEqualTo(String value) {
            addCriterion("bill_type =", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotEqualTo(String value) {
            addCriterion("bill_type <>", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThan(String value) {
            addCriterion("bill_type >", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bill_type >=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThan(String value) {
            addCriterion("bill_type <", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThanOrEqualTo(String value) {
            addCriterion("bill_type <=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLike(String value) {
            addCriterion("bill_type like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotLike(String value) {
            addCriterion("bill_type not like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeIn(List<String> values) {
            addCriterion("bill_type in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotIn(List<String> values) {
            addCriterion("bill_type not in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeBetween(String value1, String value2) {
            addCriterion("bill_type between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotBetween(String value1, String value2) {
            addCriterion("bill_type not between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andBankDepositIsNull() {
            addCriterion("bank_deposit is null");
            return (Criteria) this;
        }

        public Criteria andBankDepositIsNotNull() {
            addCriterion("bank_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andBankDepositEqualTo(String value) {
            addCriterion("bank_deposit =", value, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andBankDepositNotEqualTo(String value) {
            addCriterion("bank_deposit <>", value, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andBankDepositGreaterThan(String value) {
            addCriterion("bank_deposit >", value, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andBankDepositGreaterThanOrEqualTo(String value) {
            addCriterion("bank_deposit >=", value, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andBankDepositLessThan(String value) {
            addCriterion("bank_deposit <", value, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andBankDepositLessThanOrEqualTo(String value) {
            addCriterion("bank_deposit <=", value, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andBankDepositLike(String value) {
            addCriterion("bank_deposit like", value, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andBankDepositNotLike(String value) {
            addCriterion("bank_deposit not like", value, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andBankDepositIn(List<String> values) {
            addCriterion("bank_deposit in", values, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andBankDepositNotIn(List<String> values) {
            addCriterion("bank_deposit not in", values, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andBankDepositBetween(String value1, String value2) {
            addCriterion("bank_deposit between", value1, value2, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andBankDepositNotBetween(String value1, String value2) {
            addCriterion("bank_deposit not between", value1, value2, "bankDeposit");
            return (Criteria) this;
        }

        public Criteria andOpenAccountIsNull() {
            addCriterion("open_account is null");
            return (Criteria) this;
        }

        public Criteria andOpenAccountIsNotNull() {
            addCriterion("open_account is not null");
            return (Criteria) this;
        }

        public Criteria andOpenAccountEqualTo(String value) {
            addCriterion("open_account =", value, "openAccount");
            return (Criteria) this;
        }

        public Criteria andOpenAccountNotEqualTo(String value) {
            addCriterion("open_account <>", value, "openAccount");
            return (Criteria) this;
        }

        public Criteria andOpenAccountGreaterThan(String value) {
            addCriterion("open_account >", value, "openAccount");
            return (Criteria) this;
        }

        public Criteria andOpenAccountGreaterThanOrEqualTo(String value) {
            addCriterion("open_account >=", value, "openAccount");
            return (Criteria) this;
        }

        public Criteria andOpenAccountLessThan(String value) {
            addCriterion("open_account <", value, "openAccount");
            return (Criteria) this;
        }

        public Criteria andOpenAccountLessThanOrEqualTo(String value) {
            addCriterion("open_account <=", value, "openAccount");
            return (Criteria) this;
        }

        public Criteria andOpenAccountLike(String value) {
            addCriterion("open_account like", value, "openAccount");
            return (Criteria) this;
        }

        public Criteria andOpenAccountNotLike(String value) {
            addCriterion("open_account not like", value, "openAccount");
            return (Criteria) this;
        }

        public Criteria andOpenAccountIn(List<String> values) {
            addCriterion("open_account in", values, "openAccount");
            return (Criteria) this;
        }

        public Criteria andOpenAccountNotIn(List<String> values) {
            addCriterion("open_account not in", values, "openAccount");
            return (Criteria) this;
        }

        public Criteria andOpenAccountBetween(String value1, String value2) {
            addCriterion("open_account between", value1, value2, "openAccount");
            return (Criteria) this;
        }

        public Criteria andOpenAccountNotBetween(String value1, String value2) {
            addCriterion("open_account not between", value1, value2, "openAccount");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNull() {
            addCriterion("is_enable is null");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNotNull() {
            addCriterion("is_enable is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnableEqualTo(Integer value) {
            addCriterion("is_enable =", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotEqualTo(Integer value) {
            addCriterion("is_enable <>", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThan(Integer value) {
            addCriterion("is_enable >", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_enable >=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThan(Integer value) {
            addCriterion("is_enable <", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThanOrEqualTo(Integer value) {
            addCriterion("is_enable <=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableIn(List<Integer> values) {
            addCriterion("is_enable in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotIn(List<Integer> values) {
            addCriterion("is_enable not in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableBetween(Integer value1, Integer value2) {
            addCriterion("is_enable between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("is_enable not between", value1, value2, "isEnable");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
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