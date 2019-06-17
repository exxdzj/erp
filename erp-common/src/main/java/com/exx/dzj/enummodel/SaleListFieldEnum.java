package com.exx.dzj.enummodel;

import org.apache.commons.lang3.StringUtils;

/**
 * @description 销售单导出字段
 * @author yangyun
 * @date 2019/6/17 0017
 * @return
 */
public enum SaleListFieldEnum {

    SALECODE("saleCode", "销售单编号"),
    SALEDATE("saleDate", "销售日期"),
    PAYMENTSTATUS("paymentStatus", "收款状态"),
    CUSTNAME("custName", "客户名称"),
    CUSTCODE("custCode", "客户编码"),
    CURRENCY("currency", "币别"),
    EXCHANGERATE("exchangeRate", "汇率"),
    SALESMANNAME("salesmanName", "销售员"),
    SALEPROJECT("saleProject", "项目"),
    DELIVERYADDRESS("deliveryAddress", "收货地址"),
    SALEREMARK("saleRemark", "备注"),
    ISRECEIPT("isReceipt", "是否签收"),
    SUBORDINATECOMPANYNAME("subordinateCompanyName", "所属分公司"),
    DISCOUNTAMOUNT("discountAmount", "优惠金额"),
    COLLECTEDAMOUNT("collectedAmount", "本次收款"),
    COLLECTIONACCOUNT("collectionAccount", "收款账户"),
    PAYMENTMETHOD("paymentMethod", "收款方式"),
    COLLECTIONTERMS("collectionTerms", "收款条件"),
    ACCOUNTPERIOD("accountPeriod", "账期"),
    COLLECTIONUSERNAME("collectionUserName", "制单人"),
    DUEDATE("dueDate", "到期日"),
    STOCKNAME("stockName", "存货服务"),
    STOCKADDRESS("stockAddress", "存货地点"),
    GOODSNUM("goodsNum", "数量"),
    UNITPRICE("unitPrice", "单价"),
    DISCOUNTRATE("discountRate", "折扣率%"),
    GOODSDISCOUNTAMOUNT("goodsDiscountAmount", "折扣额"),
    SALESVOLUME("salesVolume", "销售额"),
    GOODSREMARK("goodsRemark", "商品说明"),
    SUMCOLLECTEDAMOUNT("sumCollectedAmount", "已收款"),
    LOGISTICSCOMPAMYNAME("logisticsCompamyName", "物流名称"),
    FREIHTCODE("freihtCode", "快递单号"),
    DELIVERGOODSTIME("deliverGoodsTime", "发货时间"),
    SELECTORPHONENUM("selectorPhoneNum", "查询电话"),
    REMARK("remark", "物流备注"),
    RECEIVABLEACCOUN("receivableAccoun", "金额");

    private String key;
    private String name;

    private SaleListFieldEnum (String key, String name){
        this.key = key;
        this.name = name;
    }

    public SaleListFieldEnum getSaleListFieldEnum (String key){
        if (key == null){
            return null;
        }

        for (SaleListFieldEnum temp : SaleListFieldEnum.values()){
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
