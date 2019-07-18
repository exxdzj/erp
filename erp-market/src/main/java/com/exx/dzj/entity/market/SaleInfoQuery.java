package com.exx.dzj.entity.market;

import lombok.Data;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-06-09-17:11
 */
@Data
public class SaleInfoQuery extends SaleInfo {
    private static final long serialVersionUID = -285735256900205781L;

    private String startTime;
    private String endTime;

    private int type;// 1 按列表样式 2 按单据明细

    private List<Integer> idList;

    private List<String> fieldList;
    private List<String> paymentStatusList;
    private String stockName; // 商品名称
}
