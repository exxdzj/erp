package com.exx.dzj.entity.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockInfoExample() {
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

        public Criteria andNatureIsNull() {
            addCriterion("nature is null");
            return (Criteria) this;
        }

        public Criteria andNatureIsNotNull() {
            addCriterion("nature is not null");
            return (Criteria) this;
        }

        public Criteria andNatureEqualTo(String value) {
            addCriterion("nature =", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotEqualTo(String value) {
            addCriterion("nature <>", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureGreaterThan(String value) {
            addCriterion("nature >", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureGreaterThanOrEqualTo(String value) {
            addCriterion("nature >=", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLessThan(String value) {
            addCriterion("nature <", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLessThanOrEqualTo(String value) {
            addCriterion("nature <=", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLike(String value) {
            addCriterion("nature like", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotLike(String value) {
            addCriterion("nature not like", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureIn(List<String> values) {
            addCriterion("nature in", values, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotIn(List<String> values) {
            addCriterion("nature not in", values, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureBetween(String value1, String value2) {
            addCriterion("nature between", value1, value2, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotBetween(String value1, String value2) {
            addCriterion("nature not between", value1, value2, "nature");
            return (Criteria) this;
        }

        public Criteria andStockNameIsNull() {
            addCriterion("stock_name is null");
            return (Criteria) this;
        }

        public Criteria andStockNameIsNotNull() {
            addCriterion("stock_name is not null");
            return (Criteria) this;
        }

        public Criteria andStockNameEqualTo(String value) {
            addCriterion("stock_name =", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotEqualTo(String value) {
            addCriterion("stock_name <>", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameGreaterThan(String value) {
            addCriterion("stock_name >", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameGreaterThanOrEqualTo(String value) {
            addCriterion("stock_name >=", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLessThan(String value) {
            addCriterion("stock_name <", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLessThanOrEqualTo(String value) {
            addCriterion("stock_name <=", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLike(String value) {
            addCriterion("stock_name like", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotLike(String value) {
            addCriterion("stock_name not like", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameIn(List<String> values) {
            addCriterion("stock_name in", values, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotIn(List<String> values) {
            addCriterion("stock_name not in", values, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameBetween(String value1, String value2) {
            addCriterion("stock_name between", value1, value2, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotBetween(String value1, String value2) {
            addCriterion("stock_name not between", value1, value2, "stockName");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeIsNull() {
            addCriterion("specificate_type is null");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeIsNotNull() {
            addCriterion("specificate_type is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeEqualTo(String value) {
            addCriterion("specificate_type =", value, "specificateType");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeNotEqualTo(String value) {
            addCriterion("specificate_type <>", value, "specificateType");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeGreaterThan(String value) {
            addCriterion("specificate_type >", value, "specificateType");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("specificate_type >=", value, "specificateType");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeLessThan(String value) {
            addCriterion("specificate_type <", value, "specificateType");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeLessThanOrEqualTo(String value) {
            addCriterion("specificate_type <=", value, "specificateType");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeLike(String value) {
            addCriterion("specificate_type like", value, "specificateType");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeNotLike(String value) {
            addCriterion("specificate_type not like", value, "specificateType");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeIn(List<String> values) {
            addCriterion("specificate_type in", values, "specificateType");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeNotIn(List<String> values) {
            addCriterion("specificate_type not in", values, "specificateType");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeBetween(String value1, String value2) {
            addCriterion("specificate_type between", value1, value2, "specificateType");
            return (Criteria) this;
        }

        public Criteria andSpecificateTypeNotBetween(String value1, String value2) {
            addCriterion("specificate_type not between", value1, value2, "specificateType");
            return (Criteria) this;
        }

        public Criteria andStockClassIsNull() {
            addCriterion("stock_class is null");
            return (Criteria) this;
        }

        public Criteria andStockClassIsNotNull() {
            addCriterion("stock_class is not null");
            return (Criteria) this;
        }

        public Criteria andStockClassEqualTo(String value) {
            addCriterion("stock_class =", value, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockClassNotEqualTo(String value) {
            addCriterion("stock_class <>", value, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockClassGreaterThan(String value) {
            addCriterion("stock_class >", value, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockClassGreaterThanOrEqualTo(String value) {
            addCriterion("stock_class >=", value, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockClassLessThan(String value) {
            addCriterion("stock_class <", value, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockClassLessThanOrEqualTo(String value) {
            addCriterion("stock_class <=", value, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockClassLike(String value) {
            addCriterion("stock_class like", value, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockClassNotLike(String value) {
            addCriterion("stock_class not like", value, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockClassIn(List<String> values) {
            addCriterion("stock_class in", values, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockClassNotIn(List<String> values) {
            addCriterion("stock_class not in", values, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockClassBetween(String value1, String value2) {
            addCriterion("stock_class between", value1, value2, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockClassNotBetween(String value1, String value2) {
            addCriterion("stock_class not between", value1, value2, "stockClass");
            return (Criteria) this;
        }

        public Criteria andStockAddressIsNull() {
            addCriterion("stock_address is null");
            return (Criteria) this;
        }

        public Criteria andStockAddressIsNotNull() {
            addCriterion("stock_address is not null");
            return (Criteria) this;
        }

        public Criteria andStockAddressEqualTo(String value) {
            addCriterion("stock_address =", value, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andStockAddressNotEqualTo(String value) {
            addCriterion("stock_address <>", value, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andStockAddressGreaterThan(String value) {
            addCriterion("stock_address >", value, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andStockAddressGreaterThanOrEqualTo(String value) {
            addCriterion("stock_address >=", value, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andStockAddressLessThan(String value) {
            addCriterion("stock_address <", value, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andStockAddressLessThanOrEqualTo(String value) {
            addCriterion("stock_address <=", value, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andStockAddressLike(String value) {
            addCriterion("stock_address like", value, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andStockAddressNotLike(String value) {
            addCriterion("stock_address not like", value, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andStockAddressIn(List<String> values) {
            addCriterion("stock_address in", values, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andStockAddressNotIn(List<String> values) {
            addCriterion("stock_address not in", values, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andStockAddressBetween(String value1, String value2) {
            addCriterion("stock_address between", value1, value2, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andStockAddressNotBetween(String value1, String value2) {
            addCriterion("stock_address not between", value1, value2, "stockAddress");
            return (Criteria) this;
        }

        public Criteria andMeterUnitIsNull() {
            addCriterion("meter_unit is null");
            return (Criteria) this;
        }

        public Criteria andMeterUnitIsNotNull() {
            addCriterion("meter_unit is not null");
            return (Criteria) this;
        }

        public Criteria andMeterUnitEqualTo(String value) {
            addCriterion("meter_unit =", value, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andMeterUnitNotEqualTo(String value) {
            addCriterion("meter_unit <>", value, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andMeterUnitGreaterThan(String value) {
            addCriterion("meter_unit >", value, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andMeterUnitGreaterThanOrEqualTo(String value) {
            addCriterion("meter_unit >=", value, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andMeterUnitLessThan(String value) {
            addCriterion("meter_unit <", value, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andMeterUnitLessThanOrEqualTo(String value) {
            addCriterion("meter_unit <=", value, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andMeterUnitLike(String value) {
            addCriterion("meter_unit like", value, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andMeterUnitNotLike(String value) {
            addCriterion("meter_unit not like", value, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andMeterUnitIn(List<String> values) {
            addCriterion("meter_unit in", values, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andMeterUnitNotIn(List<String> values) {
            addCriterion("meter_unit not in", values, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andMeterUnitBetween(String value1, String value2) {
            addCriterion("meter_unit between", value1, value2, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andMeterUnitNotBetween(String value1, String value2) {
            addCriterion("meter_unit not between", value1, value2, "meterUnit");
            return (Criteria) this;
        }

        public Criteria andPicturesIsNull() {
            addCriterion("pictures is null");
            return (Criteria) this;
        }

        public Criteria andPicturesIsNotNull() {
            addCriterion("pictures is not null");
            return (Criteria) this;
        }

        public Criteria andPicturesEqualTo(String value) {
            addCriterion("pictures =", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotEqualTo(String value) {
            addCriterion("pictures <>", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesGreaterThan(String value) {
            addCriterion("pictures >", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesGreaterThanOrEqualTo(String value) {
            addCriterion("pictures >=", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesLessThan(String value) {
            addCriterion("pictures <", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesLessThanOrEqualTo(String value) {
            addCriterion("pictures <=", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesLike(String value) {
            addCriterion("pictures like", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotLike(String value) {
            addCriterion("pictures not like", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesIn(List<String> values) {
            addCriterion("pictures in", values, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotIn(List<String> values) {
            addCriterion("pictures not in", values, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesBetween(String value1, String value2) {
            addCriterion("pictures between", value1, value2, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotBetween(String value1, String value2) {
            addCriterion("pictures not between", value1, value2, "pictures");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNull() {
            addCriterion("bar_code is null");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNotNull() {
            addCriterion("bar_code is not null");
            return (Criteria) this;
        }

        public Criteria andBarCodeEqualTo(String value) {
            addCriterion("bar_code =", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotEqualTo(String value) {
            addCriterion("bar_code <>", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThan(String value) {
            addCriterion("bar_code >", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bar_code >=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThan(String value) {
            addCriterion("bar_code <", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThanOrEqualTo(String value) {
            addCriterion("bar_code <=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLike(String value) {
            addCriterion("bar_code like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotLike(String value) {
            addCriterion("bar_code not like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeIn(List<String> values) {
            addCriterion("bar_code in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotIn(List<String> values) {
            addCriterion("bar_code not in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeBetween(String value1, String value2) {
            addCriterion("bar_code between", value1, value2, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotBetween(String value1, String value2) {
            addCriterion("bar_code not between", value1, value2, "barCode");
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

        public Criteria andIsCommSaleIsNull() {
            addCriterion("is_comm_sale is null");
            return (Criteria) this;
        }

        public Criteria andIsCommSaleIsNotNull() {
            addCriterion("is_comm_sale is not null");
            return (Criteria) this;
        }

        public Criteria andIsCommSaleEqualTo(Integer value) {
            addCriterion("is_comm_sale =", value, "isCommSale");
            return (Criteria) this;
        }

        public Criteria andIsCommSaleNotEqualTo(Integer value) {
            addCriterion("is_comm_sale <>", value, "isCommSale");
            return (Criteria) this;
        }

        public Criteria andIsCommSaleGreaterThan(Integer value) {
            addCriterion("is_comm_sale >", value, "isCommSale");
            return (Criteria) this;
        }

        public Criteria andIsCommSaleGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_comm_sale >=", value, "isCommSale");
            return (Criteria) this;
        }

        public Criteria andIsCommSaleLessThan(Integer value) {
            addCriterion("is_comm_sale <", value, "isCommSale");
            return (Criteria) this;
        }

        public Criteria andIsCommSaleLessThanOrEqualTo(Integer value) {
            addCriterion("is_comm_sale <=", value, "isCommSale");
            return (Criteria) this;
        }

        public Criteria andIsCommSaleIn(List<Integer> values) {
            addCriterion("is_comm_sale in", values, "isCommSale");
            return (Criteria) this;
        }

        public Criteria andIsCommSaleNotIn(List<Integer> values) {
            addCriterion("is_comm_sale not in", values, "isCommSale");
            return (Criteria) this;
        }

        public Criteria andIsCommSaleBetween(Integer value1, Integer value2) {
            addCriterion("is_comm_sale between", value1, value2, "isCommSale");
            return (Criteria) this;
        }

        public Criteria andIsCommSaleNotBetween(Integer value1, Integer value2) {
            addCriterion("is_comm_sale not between", value1, value2, "isCommSale");
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