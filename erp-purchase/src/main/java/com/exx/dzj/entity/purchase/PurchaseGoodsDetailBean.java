package com.exx.dzj.entity.purchase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode(of = "id")
public class PurchaseGoodsDetailBean implements Serializable {
    private Integer id;

    private String purchaseCode;

    private String stockCode;

    private String stockName;

    private String stockAddressCode;

    private String stockAddress;

    private Integer goodsNum;

    private BigDecimal unitPrice;

    private String priceType;

    private BigDecimal discountRate;

    private BigDecimal discountAmount;

    private BigDecimal purchaseVolume;

    private String remarks;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}