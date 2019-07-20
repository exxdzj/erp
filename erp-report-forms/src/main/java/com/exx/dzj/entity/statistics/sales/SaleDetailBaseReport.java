package com.exx.dzj.entity.statistics.sales;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author yangyun
 * @create 2019-06-26-11:10
 */
@Data
public class SaleDetailBaseReport implements Serializable {

    private static final long serialVersionUID = -8284084553679609787L;

    private String saleCode;
    private Timestamp saleDate;
    private String salesmanCode;
    private String realName;
    private String custCode;
    private String custName;
    private Double goodsNum;
    private BigDecimal unitPrice;
    private BigDecimal discountAmount;
    private String stockAddress;
    private String stockCode;
    private String stockName;
    private Double exchangeRate;
    private BigDecimal originalSaleVolume; // 本币销售额 = 数量 * 单价 * 币别
}
