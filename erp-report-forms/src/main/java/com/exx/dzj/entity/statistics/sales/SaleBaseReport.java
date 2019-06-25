package com.exx.dzj.entity.statistics.sales;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author yangyun
 * @create 2019-06-24-19:59
 */
@Data
public class SaleBaseReport implements Serializable {

    private static final long serialVersionUID = 3961916382012422515L;
    private String stockCode;
    private String stockName;
    private String saleCode;
    private Timestamp saleDate;
    private String custCode;
    private String custName;
    private BigDecimal unitPrice;
    private BigDecimal standardBuyPrice;
    private Double goodsNum;
    private String realName;
}
