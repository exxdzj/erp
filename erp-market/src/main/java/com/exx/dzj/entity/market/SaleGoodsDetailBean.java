package com.exx.dzj.entity.market;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class SaleGoodsDetailBean implements Serializable {
    private Integer id;

    private String saleCode;

    private String stockCode;

    private String stockName;

    private String stockAddressCode;

    private String stockAddress;

    private Integer goodsNum;

    private BigDecimal unitPrice;

    private String priceType;

    private BigDecimal discountRate;

    private BigDecimal discountAmount;

    private BigDecimal salesVolume;

    private String remarks;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}