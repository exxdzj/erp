package com.exx.dzj.entity.purchase;

import lombok.Data;

@Data
public class PurchaseQuery extends PurchaseInfo {
    private String startTime;
    private String endTime;

    private int type;// 1 按列表样式 2 按单据明细
}
