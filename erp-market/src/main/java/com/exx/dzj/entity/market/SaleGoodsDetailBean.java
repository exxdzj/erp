package com.exx.dzj.entity.market;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode(of = "id")
public class SaleGoodsDetailBean implements Serializable {
    private Integer id;

    private String saleCode;

    private String stockCode;

    private String stockName;

    private String stockAddressCode;

    private String stockAddress;

    private Double goodsNum;

    private BigDecimal unitPrice;

    private BigDecimal realSellPrice;

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