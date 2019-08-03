package com.exx.dzj.entity.statistics.sales;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 销售提成汇总
 * @author yangyun
 * @date 2019/4/26 0026
 * @return
 */
@Data
public class SaleDeductionReport implements Serializable {

    private static final long serialVersionUID = -7489255878436211632L;

    private Integer id;
    private String userCode; // 销售员编码
    private String salesmanCode; // 销售员编码
    private String realName; // 名称
    private double sumGoodsNum; // 商品数量
    private BigDecimal sumSaleVolume; // 销售额
    private BigDecimal sumSaleCost; //  商品成本
    private BigDecimal sumGrossMargin; // 毛利
    private BigDecimal grossRate; // 毛利率
    private BigDecimal sumCost; // 费用
    private BigDecimal pureProfit; // 纯利润
    private BigDecimal commissionRate; // 佣金率
    private BigDecimal commission; // 佣金
}
