package com.exx.dzj.entity.statistics.sales;

import lombok.Data;

/**
 * @description: 销售统计依存货查询数据
 * @author yangyun
 * @date 2019/6/24 0024
 */
@Data
public class StockBaseReport extends SaleBaseReport {
    private static final long serialVersionUID = 8366768025065812151L;

    private String stockClassName;

    private String stockAddress;

}
