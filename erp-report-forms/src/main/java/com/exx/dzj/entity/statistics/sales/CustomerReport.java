package com.exx.dzj.entity.statistics.sales;

import com.exx.dzj.entity.customer.CustomerSupplierBean;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-04-22-10:16
 */
@Data
@ToString(callSuper = true)
public class CustomerReport extends CustomerSupplierBean {

    private static final long serialVersionUID = -8751834605624070698L;

    private List<SaleInfoReport> saleInfoReportList = new ArrayList<>();

    private double totalGoodsNum;

    private BigDecimal totalSaleVolume;

    private BigDecimal totalCost;

    private BigDecimal totalGrossMargin;

    private BigDecimal totalGrossRate;
}
