package com.exx.dzj.entity.statistics.sales;

import com.exx.dzj.entity.market.SaleInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-04-19-15:59
 */
@Data
public class SaleInfoReport extends SaleInfo {
    private static final long serialVersionUID = 8856171790032864975L;

    private List<SaleGoodsReport> saleGoodsReportList;

    private BigDecimal collectedAmountTotal;

    private double sumGoodsNum;

    private BigDecimal sumSaleVolume;

    private BigDecimal sumCost;

    private BigDecimal sumGrossMargin;

    private BigDecimal sumGrossRate;
}
