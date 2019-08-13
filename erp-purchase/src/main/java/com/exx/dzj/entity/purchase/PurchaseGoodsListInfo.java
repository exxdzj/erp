package com.exx.dzj.entity.purchase;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName PurchaseGoodsListInfo
 * @Description:
 * @Author yangyun
 * @Date 2019/8/8 0008 9:21
 * @Version 1.0
 **/
@Data
public class PurchaseGoodsListInfo implements Serializable {
    private static final long serialVersionUID = -3039388186359942807L;

    private String stockCode; // 存货编号
    private String stockName; // 存货名称
    private String stockAddress;// 存货地点
    private Double goodsNum; // 数量
    private BigDecimal realSellPrice; // 实际采购单价
    private BigDecimal purchaseVolume; // 采购额
    private String goodsRemark; // 商品备注
}
