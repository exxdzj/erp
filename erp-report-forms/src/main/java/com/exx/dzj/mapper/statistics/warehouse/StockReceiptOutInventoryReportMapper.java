package com.exx.dzj.mapper.statistics.warehouse;

import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.statistics.warehouse.StockReceiptOutReport;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-09-09-18:14
 */
public interface StockReceiptOutInventoryReportMapper {

    List<StockReceiptOutReport> queryReceiptOutInventoryList (StockInfoQuery query);
}
