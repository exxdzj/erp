package com.exx.dzj.entity.stock;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author
 * @Date 2019/1/14 0014 9:53
 * @Description
 */
@Data
@ToString
public class StockModel implements Serializable {
    private Integer id;
    private String value;
    private String stockCode;
    private String stockNameForSpe;
    private String stockClassName;
    private BigDecimal standardBuyPrice;
    private BigDecimal standardSellPrice;
    private String natureName;
    private int isShelves;
    private String shelvesName;
    private String stockAddress;
    private String stockAddressCode;
    private Integer minInventory;

    /*仓库名称*/
    private String warehouseName;
}
