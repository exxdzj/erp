package com.exx.dzj.service.statistics.warehouse;

import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.statistics.warehouse.StockReceiptOutReport;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StockReceiptOutInventoryReportService
 * @Description:
 * @Author yangyun
 * @Date 2019/9/9 0009 18:03
 * @Version 1.0
 **/
public interface StockReceiptOutInventoryReportService {

    public List<StockReceiptOutReport> queryReceiptOutInventoryList (StockInfoQuery query);
}
