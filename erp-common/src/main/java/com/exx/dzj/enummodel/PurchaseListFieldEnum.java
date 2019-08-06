package com.exx.dzj.enummodel;

import org.apache.commons.lang3.StringUtils;

/**
 * @description: 采购单列表导出可选字段
 * @author yangyun
 * @date 2019/8/6 0006
 */
public enum PurchaseListFieldEnum {
    purchaseDate("purchaseDate", "采购时间"),
    purchaseCode("purchaseCode", "采购单号"),
    paymentStatus("paymentStatus", "状态"),
    custName("custName", "供应商"),
    custCode("custCode", "供应商编码"),
    custPhoneNum("custPhoneNum", "供应商电话"),
    currency("currency", "币别"),
    exchangeRate("exchangeRate", "汇率"),
    deliveryAddress("deliveryAddress", "送货地址"),
    salesmanName("salesmanName", "采购员"),
    purchaseProject("purchaseProject", "项目"),
    purchaseOrderCode("purchaseOrderCode", "采购订单号"),
    invoiceCode("invoiceCode", "发票编号"),
    purchaseRemark("purchaseRemark", "采购单备注"),
    purchaseSumVolume("purchaseSumVolume", "采购总额"),
    discountAmount("discountAmount", "优惠金额"),
    collectionTerms("collectionTerms", "付款条件"),
    accountPeriod("accountPeriod", "账期"),
    createUser("createUser", "操作员"),

    collectedAmount("collectedAmount", "付款金额"),
    collectionAccount("collectionAccount", "付款账号"),
    paymentMethod("paymentMethod", "付款方式"),

    stockCode("stockCode", "存货编号"),
    stockName("stockName", "存货名称"),
    stockAddress("stockAddress", "存货地点"),
    goodsNum("goodsNum", "商品数量"),
    realSellPrice("realSellPrice", "采购单价"),
    purchaseVolume("purchaseVolume", "采购额"),
    goodsRemark("goodsRemark", "商品备注");

    private String key;
    private String name;

    private PurchaseListFieldEnum (String key, String name){
        this.key = key;
        this.name = name;
    }

    public PurchaseListFieldEnum getSaleListFieldEnum (String key){
        if (key == null){
            return null;
        }

        for (PurchaseListFieldEnum temp : PurchaseListFieldEnum.values()){
            if (StringUtils.equals(temp.getKey(), key)){
                return temp;
            }
        }

        return null;
    }

    public String getKey (){
        return key;
    }

    public String getName (){
        return name;
    }
}
