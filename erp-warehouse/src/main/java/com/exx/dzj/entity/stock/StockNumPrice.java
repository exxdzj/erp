package com.exx.dzj.entity.stock;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class StockNumPrice implements Serializable {
    private Integer id;

    private String stockCode;

    private BigDecimal standardBuyPrice;

    private BigDecimal standardSellPrice;

    private BigDecimal maxPurchasePrice;

    private BigDecimal minSellPrice;

    private Integer minInventory;

    /**存货地点编码*/
    private String stockAddressCode;
    /**存货地点*/
    private String stockAddress;
    /**预警库存数量*/
    private Integer warnNum;
    /**是否是默认存货地址*/
    private Integer isDefault;

    private Integer isEnable;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}