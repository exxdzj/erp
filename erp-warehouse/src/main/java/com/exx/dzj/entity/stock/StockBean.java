package com.exx.dzj.entity.stock;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author
 * @Date 2019/1/12 0012 18:27
 * @Description 存货
 */
@Data
@ToString
public class StockBean extends StockInfo implements Serializable {

    private String[] images;

    private BigDecimal standardBuyPrice;

    private BigDecimal standardSellPrice;

    private BigDecimal maxPurchasePrice;

    private BigDecimal minSellPrice;

    private Integer minInventory;
    /**默认存货地点编码*/
    private String stockAddressCode;
    /**默认存货地点*/
    private String stockAddress;

    private String dialogStatus;
}
