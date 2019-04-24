package com.exx.dzj.entity.statistics.sales;

import com.exx.dzj.page.BaseModule;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-04-11-11:44
 */
@Data
public class StockTypeReport extends BaseModule {
    private static final long serialVersionUID = 2001304201055170925L;

    private String stockTypeCode;

    private String stockTypeName;

    private List<StockInfoReport> stockReportList;

    private Double countTotal; // 数量合计

    private BigDecimal salesTotal; // 销售额合计

    private BigDecimal costTotal; // 成本合计

    private BigDecimal grossTotal; // 毛利合计

    private double grossRateTotal; // 毛利率合计

}
