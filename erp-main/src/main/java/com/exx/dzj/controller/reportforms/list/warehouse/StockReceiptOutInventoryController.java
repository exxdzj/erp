package com.exx.dzj.controller.reportforms.list.warehouse;

import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.statistics.warehouse.StockReceiptOutReport;
import com.exx.dzj.facade.reportforms.warehouse.StockReceiptOutInventoryReportFacade;
import com.exx.dzj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StockReceiptOutInventoryReport
 * @Description:
 * @Author yangyun
 * @Date 2019/9/9 0009 17:51
 * @Version 1.0
 **/
@RestController
@RequestMapping("stockreceiptoutinventory/")
public class StockReceiptOutInventoryController {

    @Autowired
    private StockReceiptOutInventoryReportFacade stockReceiptOutInventoryReportFacade;

    /**
     * @description: 存货收发汇总
     * @author yangyun
     * @date 2019/9/10 0010
     * @param query
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("/queryreceiptoutinventorylist")
    public Result queryReceiptOutInventoryList (StockInfoQuery query){
        Result result = Result.responseSuccess();
        Map<String, Object> listMap = stockReceiptOutInventoryReportFacade.queryReceiptOutInventoryList(query);

        result.setData(listMap);
        return result;
    }
}
