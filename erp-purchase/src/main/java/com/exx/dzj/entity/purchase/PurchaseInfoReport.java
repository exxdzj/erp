package com.exx.dzj.entity.purchase;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class PurchaseInfoReport extends PurchaseInfo{

    private String realName;
    private List<PurchaseGoodsReport> goodsReports = new ArrayList<>();

    private Double goodsNumTotal;
    private BigDecimal purchaseVolumeTotal;
}
