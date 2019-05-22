package com.exx.dzj.entity.purchase;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yangyun
 * @create 2019-05-22-16:36
 */
@Data
public class PurchaseHistoryInfo implements Serializable {
    private static final long serialVersionUID = 8678517521686211258L;

    private String purchaseCode; // 采购单号
    private String stockAddress; // 进货仓库
    private double goodsNum;
    private BigDecimal realSellPrice; // 实际采购价格
    private BigDecimal standardBuyPrice; // 标准买价
    private String custName; // 供应商
    private String realName; // 采购员
}
