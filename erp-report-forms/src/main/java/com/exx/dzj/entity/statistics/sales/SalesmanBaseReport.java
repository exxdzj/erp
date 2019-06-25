package com.exx.dzj.entity.statistics.sales;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 销售单统计依销售员查询数据
 * @author yangyun
 * @date 2019/6/24 0024
 */
@Data
public class SalesmanBaseReport extends SaleBaseReport {
    private static final long serialVersionUID = -1603646040601938505L;

    private Integer commissionRate; // 提成率
    private String userCode;
    private String salesmanCode;
    private BigDecimal collectedAmount;
}
