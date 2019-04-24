package com.exx.dzj.entity.statistics.sales;

import com.exx.dzj.entity.stock.StockInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-04-11-13:53
 */
@Data
public class StockInfoReport extends StockInfo {

    private static final long serialVersionUID = 5537503248465284200L;

    private BigDecimal standardBuyPrice;

    private List<SaleGoodsReport> saleInfoReports;

    private Double countTotal; // 数量小计

    private BigDecimal salesTotal; // 销售额小计

    private BigDecimal costTotal; // 成本小计

    private BigDecimal grossTotal; // 毛利小计

    private double grossRateTotal; // 毛利率小计
}
