package com.exx.dzj.service.statistics.warehouse.impl;

import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.statistics.warehouse.StockReceiptOutReport;
import com.exx.dzj.mapper.statistics.warehouse.StockReceiptOutInventoryReportMapper;
import com.exx.dzj.service.statistics.warehouse.StockReceiptOutInventoryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StockReceiptOutInventoryReportServiceImpl
 * @Description:
 * @Author yangyun
 * @Date 2019/9/9 0009 18:03
 * @Version 1.0
 **/
@Service("stockReceiptOutInventoryService")
public class StockReceiptOutInventoryReportServiceImpl implements StockReceiptOutInventoryReportService{

    @Autowired
    private StockReceiptOutInventoryReportMapper stockReceiptOutInventoryReportMapper;

    @Override
    public List<StockReceiptOutReport> queryReceiptOutInventoryList(StockInfoQuery query) {
        return stockReceiptOutInventoryReportMapper.queryReceiptOutInventoryList(query);
    }
}
