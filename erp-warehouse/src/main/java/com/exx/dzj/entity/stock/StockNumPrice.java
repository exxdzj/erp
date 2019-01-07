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

    private Integer isEnable;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}