package com.exx.dzj.entity.statistics.sales;

import com.exx.dzj.entity.market.SaleGoodsDetailBean;
import com.sun.tools.corba.se.idl.constExpr.Times;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author yangyun
 * @create 2019-04-11-13:51
 */
@Data
public class SaleGoodsReport extends SaleGoodsDetailBean {

    private static final long serialVersionUID = 7513589366811599404L;

    private BigDecimal standardBuyPrice;

    private BigDecimal cost; // 成本
    private BigDecimal grossMargin; // 毛利
    private BigDecimal grossRate; // 毛利率

    private BigDecimal saleIncome; // 销售额

    private String custCode;
    private String custName;

    private String salesmanCode;

    private String realName;
    private Timestamp saleDate;
}
