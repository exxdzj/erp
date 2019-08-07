package com.exx.dzj.enummodel;

import org.apache.commons.lang3.StringUtils;

/**
 * @description: 采购单列表导出可选字段
 * @author yangyun
 * @date 2019/8/6 0006
 */
public enum PurchaseListFieldEnum {
    PURCHASEDATE("purchaseDate", "采购时间"),
    PURCHASECODE("purchaseCode", "采购单号"),
    PAYMENTSTATUS("paymentStatus", "状态"),
    CUSTNAME("custName", "供应商"),
    CUSTCODE("custCode", "供应商编码"),
    CUSTPHONENUM("custPhoneNum", "供应商电话"),
    CURRENCY("currency", "币别"),
    EXCHANGERATE("exchangeRate", "汇率"),
    DELIVERYADDRESS("deliveryAddress", "送货地址"),
    SALESMANNAME("salesmanName", "采购员"),
    PURCHASEPROJECT("purchaseProject", "项目"),
    PURCHASEORDERCODE("purchaseOrderCode", "采购订单号"),
    INVOICECODE("invoiceCode", "发票编号"),
    PURCHASEREMARK("purchaseRemark", "采购单备注"),
    PURCHASESUMVOLUME("purchaseSumVolume", "采购总额"),
    DISCOUNTAMOUNT("discountAmount", "优惠金额"),
    COLLECTIONTERMS("collectionTerms", "付款条件"),
    ACCOUNTPERIOD("accountPeriod", "账期"),
    CREATEUSER("createUser", "操作员"),
    FLOWSTATUS("flowStatus", "审核状态"),

    COLLECTEDAMOUNT("collectedAmount", "付款金额"),
    COLLECTIONACCOUNT("collectionAccount", "付款账号"),
    PAYMENTMETHOD("paymentMethod", "付款方式"),

    STOCKCODE("stockCode", "存货编号"),
    STOCKNAME("stockName", "存货名称"),
    STOCKADDRESS("stockAddress", "存货地点"),
    GOODSNUM("goodsNum", "商品数量"),
    REALSELLPRICE("realSellPrice", "采购单价"),
    PURCHASEVOLUME("purchaseVolume", "采购额"),
    GOODSREMARK("goodsRemark", "商品备注");

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
