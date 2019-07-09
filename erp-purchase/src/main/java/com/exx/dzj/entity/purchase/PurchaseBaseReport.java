package com.exx.dzj.entity.purchase;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class PurchaseBaseReport implements Serializable {
    private static final long serialVersionUID = 1402297522366545171L;

    private String purchaseCode;
    private Timestamp purchaseDate;
    private Double goodsNum;
    private BigDecimal realSellPrice;
    private String custCode;
    private String custName;
    private String stockCode;
    private String stockName;
}
