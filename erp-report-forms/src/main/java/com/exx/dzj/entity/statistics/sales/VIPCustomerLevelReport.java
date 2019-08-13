package com.exx.dzj.entity.statistics.sales;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName VIPCustomerLevelReport
 * @Description:
 * @Author yangyun
 * @Date 2019/8/12 0012 17:02
 * @Version 1.0
 **/
@Data
public class VIPCustomerLevelReport implements Serializable {
    private static final long serialVersionUID = 3265197911624474820L;

    private String custCode;
    private String custName;
    private String realName;
    private Integer buyCount; // 购买次数
    private BigDecimal saleVolume; // 销售额
    private double goodsNum; // 商品数量
    private BigDecimal cost; // 成本
    private BigDecimal discountAmount; // 优惠金额
    private BigDecimal grossMargin; // 毛利
    private BigDecimal grossRate; // 毛利率
    private BigDecimal prfit; // 利润
    private String custGrade; // 客户等级名称
}
