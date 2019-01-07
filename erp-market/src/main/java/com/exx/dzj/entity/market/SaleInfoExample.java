package com.exx.dzj.entity.market;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SaleInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SaleInfoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andUserCodeIsNull() {
            addCriterion("user_code is null");
            return (Criteria) this;
        }

        public Criteria andUserCodeIsNotNull() {
            addCriterion("user_code is not null");
            return (Criteria) this;
        }

        public Criteria andUserCodeEqualTo(String value) {
            addCriterion("user_code =", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotEqualTo(String value) {
            addCriterion("user_code <>", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThan(String value) {
            addCriterion("user_code >", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("user_code >=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThan(String value) {
            addCriterion("user_code <", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThanOrEqualTo(String value) {
            addCriterion("user_code <=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLike(String value) {
            addCriterion("user_code like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotLike(String value) {
            addCriterion("user_code not like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeIn(List<String> values) {
            addCriterion("user_code in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotIn(List<String> values) {
            addCriterion("user_code not in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeBetween(String value1, String value2) {
            addCriterion("user_code between", value1, value2, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotBetween(String value1, String value2) {
            addCriterion("user_code not between", value1, value2, "userCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeIsNull() {
            addCriterion("salesman_code is null");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeIsNotNull() {
            addCriterion("salesman_code is not null");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeEqualTo(String value) {
            addCriterion("salesman_code =", value, "salesmanCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeNotEqualTo(String value) {
            addCriterion("salesman_code <>", value, "salesmanCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeGreaterThan(String value) {
            addCriterion("salesman_code >", value, "salesmanCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeGreaterThanOrEqualTo(String value) {
            addCriterion("salesman_code >=", value, "salesmanCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeLessThan(String value) {
            addCriterion("salesman_code <", value, "salesmanCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeLessThanOrEqualTo(String value) {
            addCriterion("salesman_code <=", value, "salesmanCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeLike(String value) {
            addCriterion("salesman_code like", value, "salesmanCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeNotLike(String value) {
            addCriterion("salesman_code not like", value, "salesmanCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeIn(List<String> values) {
            addCriterion("salesman_code in", values, "salesmanCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeNotIn(List<String> values) {
            addCriterion("salesman_code not in", values, "salesmanCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeBetween(String value1, String value2) {
            addCriterion("salesman_code between", value1, value2, "salesmanCode");
            return (Criteria) this;
        }

        public Criteria andSalesmanCodeNotBetween(String value1, String value2) {
            addCriterion("salesman_code not between", value1, value2, "salesmanCode");
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

        public Criteria andSaleProjectIsNull() {
            addCriterion("sale_project is null");
            return (Criteria) this;
        }

        public Criteria andSaleProjectIsNotNull() {
            addCriterion("sale_project is not null");
            return (Criteria) this;
        }

        public Criteria andSaleProjectEqualTo(String value) {
            addCriterion("sale_project =", value, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleProjectNotEqualTo(String value) {
            addCriterion("sale_project <>", value, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleProjectGreaterThan(String value) {
            addCriterion("sale_project >", value, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleProjectGreaterThanOrEqualTo(String value) {
            addCriterion("sale_project >=", value, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleProjectLessThan(String value) {
            addCriterion("sale_project <", value, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleProjectLessThanOrEqualTo(String value) {
            addCriterion("sale_project <=", value, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleProjectLike(String value) {
            addCriterion("sale_project like", value, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleProjectNotLike(String value) {
            addCriterion("sale_project not like", value, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleProjectIn(List<String> values) {
            addCriterion("sale_project in", values, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleProjectNotIn(List<String> values) {
            addCriterion("sale_project not in", values, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleProjectBetween(String value1, String value2) {
            addCriterion("sale_project between", value1, value2, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleProjectNotBetween(String value1, String value2) {
            addCriterion("sale_project not between", value1, value2, "saleProject");
            return (Criteria) this;
        }

        public Criteria andSaleDateIsNull() {
            addCriterion("sale_date is null");
            return (Criteria) this;
        }

        public Criteria andSaleDateIsNotNull() {
            addCriterion("sale_date is not null");
            return (Criteria) this;
        }

        public Criteria andSaleDateEqualTo(Date value) {
            addCriterionForJDBCDate("sale_date =", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("sale_date <>", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateGreaterThan(Date value) {
            addCriterionForJDBCDate("sale_date >", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sale_date >=", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateLessThan(Date value) {
            addCriterionForJDBCDate("sale_date <", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sale_date <=", value, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateIn(List<Date> values) {
            addCriterionForJDBCDate("sale_date in", values, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("sale_date not in", values, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sale_date between", value1, value2, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSaleDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sale_date not between", value1, value2, "saleDate");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeIsNull() {
            addCriterion("sales_order_code is null");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeIsNotNull() {
            addCriterion("sales_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeEqualTo(String value) {
            addCriterion("sales_order_code =", value, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeNotEqualTo(String value) {
            addCriterion("sales_order_code <>", value, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeGreaterThan(String value) {
            addCriterion("sales_order_code >", value, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sales_order_code >=", value, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeLessThan(String value) {
            addCriterion("sales_order_code <", value, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("sales_order_code <=", value, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeLike(String value) {
            addCriterion("sales_order_code like", value, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeNotLike(String value) {
            addCriterion("sales_order_code not like", value, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeIn(List<String> values) {
            addCriterion("sales_order_code in", values, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeNotIn(List<String> values) {
            addCriterion("sales_order_code not in", values, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeBetween(String value1, String value2) {
            addCriterion("sales_order_code between", value1, value2, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andSalesOrderCodeNotBetween(String value1, String value2) {
            addCriterion("sales_order_code not between", value1, value2, "salesOrderCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andExchangeRateIsNull() {
            addCriterion("exchange_rate is null");
            return (Criteria) this;
        }

        public Criteria andExchangeRateIsNotNull() {
            addCriterion("exchange_rate is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeRateEqualTo(Double value) {
            addCriterion("exchange_rate =", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotEqualTo(Double value) {
            addCriterion("exchange_rate <>", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateGreaterThan(Double value) {
            addCriterion("exchange_rate >", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateGreaterThanOrEqualTo(Double value) {
            addCriterion("exchange_rate >=", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateLessThan(Double value) {
            addCriterion("exchange_rate <", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateLessThanOrEqualTo(Double value) {
            addCriterion("exchange_rate <=", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateIn(List<Double> values) {
            addCriterion("exchange_rate in", values, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotIn(List<Double> values) {
            addCriterion("exchange_rate not in", values, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateBetween(Double value1, Double value2) {
            addCriterion("exchange_rate between", value1, value2, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotBetween(Double value1, Double value2) {
            addCriterion("exchange_rate not between", value1, value2, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeIsNull() {
            addCriterion("delivery_order_code is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeIsNotNull() {
            addCriterion("delivery_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeEqualTo(String value) {
            addCriterion("delivery_order_code =", value, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeNotEqualTo(String value) {
            addCriterion("delivery_order_code <>", value, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeGreaterThan(String value) {
            addCriterion("delivery_order_code >", value, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_order_code >=", value, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeLessThan(String value) {
            addCriterion("delivery_order_code <", value, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("delivery_order_code <=", value, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeLike(String value) {
            addCriterion("delivery_order_code like", value, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeNotLike(String value) {
            addCriterion("delivery_order_code not like", value, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeIn(List<String> values) {
            addCriterion("delivery_order_code in", values, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeNotIn(List<String> values) {
            addCriterion("delivery_order_code not in", values, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeBetween(String value1, String value2) {
            addCriterion("delivery_order_code between", value1, value2, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderCodeNotBetween(String value1, String value2) {
            addCriterion("delivery_order_code not between", value1, value2, "deliveryOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeIsNull() {
            addCriterion("cust_order_code is null");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeIsNotNull() {
            addCriterion("cust_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeEqualTo(String value) {
            addCriterion("cust_order_code =", value, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeNotEqualTo(String value) {
            addCriterion("cust_order_code <>", value, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeGreaterThan(String value) {
            addCriterion("cust_order_code >", value, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cust_order_code >=", value, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeLessThan(String value) {
            addCriterion("cust_order_code <", value, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("cust_order_code <=", value, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeLike(String value) {
            addCriterion("cust_order_code like", value, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeNotLike(String value) {
            addCriterion("cust_order_code not like", value, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeIn(List<String> values) {
            addCriterion("cust_order_code in", values, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeNotIn(List<String> values) {
            addCriterion("cust_order_code not in", values, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeBetween(String value1, String value2) {
            addCriterion("cust_order_code between", value1, value2, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andCustOrderCodeNotBetween(String value1, String value2) {
            addCriterion("cust_order_code not between", value1, value2, "custOrderCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIsNull() {
            addCriterion("delivery_address is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIsNotNull() {
            addCriterion("delivery_address is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressEqualTo(String value) {
            addCriterion("delivery_address =", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotEqualTo(String value) {
            addCriterion("delivery_address <>", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressGreaterThan(String value) {
            addCriterion("delivery_address >", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_address >=", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLessThan(String value) {
            addCriterion("delivery_address <", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLessThanOrEqualTo(String value) {
            addCriterion("delivery_address <=", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLike(String value) {
            addCriterion("delivery_address like", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotLike(String value) {
            addCriterion("delivery_address not like", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIn(List<String> values) {
            addCriterion("delivery_address in", values, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotIn(List<String> values) {
            addCriterion("delivery_address not in", values, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressBetween(String value1, String value2) {
            addCriterion("delivery_address between", value1, value2, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotBetween(String value1, String value2) {
            addCriterion("delivery_address not between", value1, value2, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIsNull() {
            addCriterion("invoice_code is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIsNotNull() {
            addCriterion("invoice_code is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeEqualTo(String value) {
            addCriterion("invoice_code =", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotEqualTo(String value) {
            addCriterion("invoice_code <>", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeGreaterThan(String value) {
            addCriterion("invoice_code >", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_code >=", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLessThan(String value) {
            addCriterion("invoice_code <", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLessThanOrEqualTo(String value) {
            addCriterion("invoice_code <=", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLike(String value) {
            addCriterion("invoice_code like", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotLike(String value) {
            addCriterion("invoice_code not like", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIn(List<String> values) {
            addCriterion("invoice_code in", values, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotIn(List<String> values) {
            addCriterion("invoice_code not in", values, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeBetween(String value1, String value2) {
            addCriterion("invoice_code between", value1, value2, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotBetween(String value1, String value2) {
            addCriterion("invoice_code not between", value1, value2, "invoiceCode");
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

        public Criteria andTotalSumIsNull() {
            addCriterion("total_sum is null");
            return (Criteria) this;
        }

        public Criteria andTotalSumIsNotNull() {
            addCriterion("total_sum is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSumEqualTo(BigDecimal value) {
            addCriterion("total_sum =", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumNotEqualTo(BigDecimal value) {
            addCriterion("total_sum <>", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumGreaterThan(BigDecimal value) {
            addCriterion("total_sum >", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_sum >=", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumLessThan(BigDecimal value) {
            addCriterion("total_sum <", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_sum <=", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumIn(List<BigDecimal> values) {
            addCriterion("total_sum in", values, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumNotIn(List<BigDecimal> values) {
            addCriterion("total_sum not in", values, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_sum between", value1, value2, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_sum not between", value1, value2, "totalSum");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountIsNull() {
            addCriterion("discount_amount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountIsNotNull() {
            addCriterion("discount_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountEqualTo(BigDecimal value) {
            addCriterion("discount_amount =", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountNotEqualTo(BigDecimal value) {
            addCriterion("discount_amount <>", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountGreaterThan(BigDecimal value) {
            addCriterion("discount_amount >", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_amount >=", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountLessThan(BigDecimal value) {
            addCriterion("discount_amount <", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_amount <=", value, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountIn(List<BigDecimal> values) {
            addCriterion("discount_amount in", values, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountNotIn(List<BigDecimal> values) {
            addCriterion("discount_amount not in", values, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_amount between", value1, value2, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_amount not between", value1, value2, "discountAmount");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounIsNull() {
            addCriterion("receivable_accoun is null");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounIsNotNull() {
            addCriterion("receivable_accoun is not null");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounEqualTo(BigDecimal value) {
            addCriterion("receivable_accoun =", value, "receivableAccoun");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounNotEqualTo(BigDecimal value) {
            addCriterion("receivable_accoun <>", value, "receivableAccoun");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounGreaterThan(BigDecimal value) {
            addCriterion("receivable_accoun >", value, "receivableAccoun");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable_accoun >=", value, "receivableAccoun");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounLessThan(BigDecimal value) {
            addCriterion("receivable_accoun <", value, "receivableAccoun");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounLessThanOrEqualTo(BigDecimal value) {
            addCriterion("receivable_accoun <=", value, "receivableAccoun");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounIn(List<BigDecimal> values) {
            addCriterion("receivable_accoun in", values, "receivableAccoun");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounNotIn(List<BigDecimal> values) {
            addCriterion("receivable_accoun not in", values, "receivableAccoun");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable_accoun between", value1, value2, "receivableAccoun");
            return (Criteria) this;
        }

        public Criteria andReceivableAccounNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receivable_accoun not between", value1, value2, "receivableAccoun");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounIsNull() {
            addCriterion("received_amoun is null");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounIsNotNull() {
            addCriterion("received_amoun is not null");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounEqualTo(BigDecimal value) {
            addCriterion("received_amoun =", value, "receivedAmoun");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounNotEqualTo(BigDecimal value) {
            addCriterion("received_amoun <>", value, "receivedAmoun");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounGreaterThan(BigDecimal value) {
            addCriterion("received_amoun >", value, "receivedAmoun");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("received_amoun >=", value, "receivedAmoun");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounLessThan(BigDecimal value) {
            addCriterion("received_amoun <", value, "receivedAmoun");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounLessThanOrEqualTo(BigDecimal value) {
            addCriterion("received_amoun <=", value, "receivedAmoun");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounIn(List<BigDecimal> values) {
            addCriterion("received_amoun in", values, "receivedAmoun");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounNotIn(List<BigDecimal> values) {
            addCriterion("received_amoun not in", values, "receivedAmoun");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("received_amoun between", value1, value2, "receivedAmoun");
            return (Criteria) this;
        }

        public Criteria andReceivedAmounNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("received_amoun not between", value1, value2, "receivedAmoun");
            return (Criteria) this;
        }

        public Criteria andReceiptNumIsNull() {
            addCriterion("receipt_num is null");
            return (Criteria) this;
        }

        public Criteria andReceiptNumIsNotNull() {
            addCriterion("receipt_num is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptNumEqualTo(String value) {
            addCriterion("receipt_num =", value, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andReceiptNumNotEqualTo(String value) {
            addCriterion("receipt_num <>", value, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andReceiptNumGreaterThan(String value) {
            addCriterion("receipt_num >", value, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andReceiptNumGreaterThanOrEqualTo(String value) {
            addCriterion("receipt_num >=", value, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andReceiptNumLessThan(String value) {
            addCriterion("receipt_num <", value, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andReceiptNumLessThanOrEqualTo(String value) {
            addCriterion("receipt_num <=", value, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andReceiptNumLike(String value) {
            addCriterion("receipt_num like", value, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andReceiptNumNotLike(String value) {
            addCriterion("receipt_num not like", value, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andReceiptNumIn(List<String> values) {
            addCriterion("receipt_num in", values, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andReceiptNumNotIn(List<String> values) {
            addCriterion("receipt_num not in", values, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andReceiptNumBetween(String value1, String value2) {
            addCriterion("receipt_num between", value1, value2, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andReceiptNumNotBetween(String value1, String value2) {
            addCriterion("receipt_num not between", value1, value2, "receiptNum");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsIsNull() {
            addCriterion("collection_terms is null");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsIsNotNull() {
            addCriterion("collection_terms is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsEqualTo(String value) {
            addCriterion("collection_terms =", value, "collectionTerms");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsNotEqualTo(String value) {
            addCriterion("collection_terms <>", value, "collectionTerms");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsGreaterThan(String value) {
            addCriterion("collection_terms >", value, "collectionTerms");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsGreaterThanOrEqualTo(String value) {
            addCriterion("collection_terms >=", value, "collectionTerms");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsLessThan(String value) {
            addCriterion("collection_terms <", value, "collectionTerms");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsLessThanOrEqualTo(String value) {
            addCriterion("collection_terms <=", value, "collectionTerms");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsLike(String value) {
            addCriterion("collection_terms like", value, "collectionTerms");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsNotLike(String value) {
            addCriterion("collection_terms not like", value, "collectionTerms");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsIn(List<String> values) {
            addCriterion("collection_terms in", values, "collectionTerms");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsNotIn(List<String> values) {
            addCriterion("collection_terms not in", values, "collectionTerms");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsBetween(String value1, String value2) {
            addCriterion("collection_terms between", value1, value2, "collectionTerms");
            return (Criteria) this;
        }

        public Criteria andCollectionTermsNotBetween(String value1, String value2) {
            addCriterion("collection_terms not between", value1, value2, "collectionTerms");
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

        public Criteria andDueDateIsNull() {
            addCriterion("due_date is null");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNotNull() {
            addCriterion("due_date is not null");
            return (Criteria) this;
        }

        public Criteria andDueDateEqualTo(Date value) {
            addCriterionForJDBCDate("due_date =", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("due_date <>", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThan(Date value) {
            addCriterionForJDBCDate("due_date >", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("due_date >=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThan(Date value) {
            addCriterionForJDBCDate("due_date <", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("due_date <=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIn(List<Date> values) {
            addCriterionForJDBCDate("due_date in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("due_date not in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("due_date between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("due_date not between", value1, value2, "dueDate");
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

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
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