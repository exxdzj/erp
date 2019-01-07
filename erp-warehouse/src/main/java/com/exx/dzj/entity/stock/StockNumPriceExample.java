package com.exx.dzj.entity.stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockNumPriceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockNumPriceExample() {
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

        public Criteria andStockCodeIsNull() {
            addCriterion("stock_code is null");
            return (Criteria) this;
        }

        public Criteria andStockCodeIsNotNull() {
            addCriterion("stock_code is not null");
            return (Criteria) this;
        }

        public Criteria andStockCodeEqualTo(String value) {
            addCriterion("stock_code =", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotEqualTo(String value) {
            addCriterion("stock_code <>", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThan(String value) {
            addCriterion("stock_code >", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThanOrEqualTo(String value) {
            addCriterion("stock_code >=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThan(String value) {
            addCriterion("stock_code <", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThanOrEqualTo(String value) {
            addCriterion("stock_code <=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLike(String value) {
            addCriterion("stock_code like", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotLike(String value) {
            addCriterion("stock_code not like", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeIn(List<String> values) {
            addCriterion("stock_code in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotIn(List<String> values) {
            addCriterion("stock_code not in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeBetween(String value1, String value2) {
            addCriterion("stock_code between", value1, value2, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotBetween(String value1, String value2) {
            addCriterion("stock_code not between", value1, value2, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceIsNull() {
            addCriterion("standard_buy_price is null");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceIsNotNull() {
            addCriterion("standard_buy_price is not null");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceEqualTo(BigDecimal value) {
            addCriterion("standard_buy_price =", value, "standardBuyPrice");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceNotEqualTo(BigDecimal value) {
            addCriterion("standard_buy_price <>", value, "standardBuyPrice");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceGreaterThan(BigDecimal value) {
            addCriterion("standard_buy_price >", value, "standardBuyPrice");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("standard_buy_price >=", value, "standardBuyPrice");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceLessThan(BigDecimal value) {
            addCriterion("standard_buy_price <", value, "standardBuyPrice");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("standard_buy_price <=", value, "standardBuyPrice");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceIn(List<BigDecimal> values) {
            addCriterion("standard_buy_price in", values, "standardBuyPrice");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceNotIn(List<BigDecimal> values) {
            addCriterion("standard_buy_price not in", values, "standardBuyPrice");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("standard_buy_price between", value1, value2, "standardBuyPrice");
            return (Criteria) this;
        }

        public Criteria andStandardBuyPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("standard_buy_price not between", value1, value2, "standardBuyPrice");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceIsNull() {
            addCriterion("standard_sell_price is null");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceIsNotNull() {
            addCriterion("standard_sell_price is not null");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceEqualTo(BigDecimal value) {
            addCriterion("standard_sell_price =", value, "standardSellPrice");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceNotEqualTo(BigDecimal value) {
            addCriterion("standard_sell_price <>", value, "standardSellPrice");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceGreaterThan(BigDecimal value) {
            addCriterion("standard_sell_price >", value, "standardSellPrice");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("standard_sell_price >=", value, "standardSellPrice");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceLessThan(BigDecimal value) {
            addCriterion("standard_sell_price <", value, "standardSellPrice");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("standard_sell_price <=", value, "standardSellPrice");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceIn(List<BigDecimal> values) {
            addCriterion("standard_sell_price in", values, "standardSellPrice");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceNotIn(List<BigDecimal> values) {
            addCriterion("standard_sell_price not in", values, "standardSellPrice");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("standard_sell_price between", value1, value2, "standardSellPrice");
            return (Criteria) this;
        }

        public Criteria andStandardSellPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("standard_sell_price not between", value1, value2, "standardSellPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceIsNull() {
            addCriterion("max_purchase_price is null");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceIsNotNull() {
            addCriterion("max_purchase_price is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceEqualTo(BigDecimal value) {
            addCriterion("max_purchase_price =", value, "maxPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceNotEqualTo(BigDecimal value) {
            addCriterion("max_purchase_price <>", value, "maxPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceGreaterThan(BigDecimal value) {
            addCriterion("max_purchase_price >", value, "maxPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("max_purchase_price >=", value, "maxPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceLessThan(BigDecimal value) {
            addCriterion("max_purchase_price <", value, "maxPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("max_purchase_price <=", value, "maxPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceIn(List<BigDecimal> values) {
            addCriterion("max_purchase_price in", values, "maxPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceNotIn(List<BigDecimal> values) {
            addCriterion("max_purchase_price not in", values, "maxPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_purchase_price between", value1, value2, "maxPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andMaxPurchasePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_purchase_price not between", value1, value2, "maxPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceIsNull() {
            addCriterion("min_sell_price is null");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceIsNotNull() {
            addCriterion("min_sell_price is not null");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceEqualTo(BigDecimal value) {
            addCriterion("min_sell_price =", value, "minSellPrice");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceNotEqualTo(BigDecimal value) {
            addCriterion("min_sell_price <>", value, "minSellPrice");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceGreaterThan(BigDecimal value) {
            addCriterion("min_sell_price >", value, "minSellPrice");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_sell_price >=", value, "minSellPrice");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceLessThan(BigDecimal value) {
            addCriterion("min_sell_price <", value, "minSellPrice");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_sell_price <=", value, "minSellPrice");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceIn(List<BigDecimal> values) {
            addCriterion("min_sell_price in", values, "minSellPrice");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceNotIn(List<BigDecimal> values) {
            addCriterion("min_sell_price not in", values, "minSellPrice");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_sell_price between", value1, value2, "minSellPrice");
            return (Criteria) this;
        }

        public Criteria andMinSellPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_sell_price not between", value1, value2, "minSellPrice");
            return (Criteria) this;
        }

        public Criteria andMinInventoryIsNull() {
            addCriterion("min_inventory is null");
            return (Criteria) this;
        }

        public Criteria andMinInventoryIsNotNull() {
            addCriterion("min_inventory is not null");
            return (Criteria) this;
        }

        public Criteria andMinInventoryEqualTo(Integer value) {
            addCriterion("min_inventory =", value, "minInventory");
            return (Criteria) this;
        }

        public Criteria andMinInventoryNotEqualTo(Integer value) {
            addCriterion("min_inventory <>", value, "minInventory");
            return (Criteria) this;
        }

        public Criteria andMinInventoryGreaterThan(Integer value) {
            addCriterion("min_inventory >", value, "minInventory");
            return (Criteria) this;
        }

        public Criteria andMinInventoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_inventory >=", value, "minInventory");
            return (Criteria) this;
        }

        public Criteria andMinInventoryLessThan(Integer value) {
            addCriterion("min_inventory <", value, "minInventory");
            return (Criteria) this;
        }

        public Criteria andMinInventoryLessThanOrEqualTo(Integer value) {
            addCriterion("min_inventory <=", value, "minInventory");
            return (Criteria) this;
        }

        public Criteria andMinInventoryIn(List<Integer> values) {
            addCriterion("min_inventory in", values, "minInventory");
            return (Criteria) this;
        }

        public Criteria andMinInventoryNotIn(List<Integer> values) {
            addCriterion("min_inventory not in", values, "minInventory");
            return (Criteria) this;
        }

        public Criteria andMinInventoryBetween(Integer value1, Integer value2) {
            addCriterion("min_inventory between", value1, value2, "minInventory");
            return (Criteria) this;
        }

        public Criteria andMinInventoryNotBetween(Integer value1, Integer value2) {
            addCriterion("min_inventory not between", value1, value2, "minInventory");
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