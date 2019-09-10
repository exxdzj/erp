package com.exx.dzj.facade.reportforms.warehouse;

import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.statistics.warehouse.StockReceiptOutReport;
import com.exx.dzj.service.statistics.warehouse.StockReceiptOutInventoryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName StockReceiptOutInventoryReportFacade
 * @Description:
 * @Author yangyun
 * @Date 2019/9/9 0009 18:01
 * @Version 1.0
 **/
@Component
public class StockReceiptOutInventoryReportFacade {

    @Autowired
    private StockReceiptOutInventoryReportService stockReceiptOutInventoryService;

    public Map<String, Object> queryReceiptOutInventoryList (StockInfoQuery query){
        List<StockReceiptOutReport> colloct = stockReceiptOutInventoryService.queryReceiptOutInventoryList(query);
        StockReceiptOutReport sum = new StockReceiptOutReport();
        double sumReNum = colloct.stream().mapToDouble(StockReceiptOutReport::getReceiptInventoryNum).sum();
        BigDecimal sumReCost = colloct.stream().map(StockReceiptOutReport::getReceiptCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        double sumOutInventoryNum = colloct.stream().mapToDouble(StockReceiptOutReport::getOutInventoryNum).sum();
        BigDecimal sumOutCost = colloct.stream().map(StockReceiptOutReport::getOutCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        double sumMinInventory = colloct.stream().mapToDouble(StockReceiptOutReport::getMinInventory).sum();
        BigDecimal sumCost = colloct.stream().map(StockReceiptOutReport::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);

        sum.setReceiptInventoryNum(sumReNum).setReceiptCost(sumReCost).setOutInventoryNum(sumOutInventoryNum).
                setOutCost(sumOutCost).setMinInventory(sumMinInventory).setCost(sumCost).setMeterUnit("总计: ");

        Map<String, List<StockReceiptOutReport>> listMap = colloct.stream().collect(Collectors.groupingBy(StockReceiptOutReport::getStockClassName));
        Collection<List<StockReceiptOutReport>> values = listMap.values();
        double reNum = 0.0;
        double outNum = 0.0;
        double minInventory = 0.0;
        BigDecimal reCost = BigDecimal.ZERO;
        BigDecimal outCost = BigDecimal.ZERO;
        BigDecimal cost = BigDecimal.ZERO;
        StockReceiptOutReport res = null;
        for (List<StockReceiptOutReport> data : values){
            res = new StockReceiptOutReport();
            reNum = data.stream().mapToDouble(StockReceiptOutReport::getReceiptInventoryNum).sum();
            reCost = data.stream().map(StockReceiptOutReport::getReceiptCost).reduce(BigDecimal.ZERO, BigDecimal::add);
            outNum = data.stream().mapToDouble(StockReceiptOutReport::getOutInventoryNum).sum();
            outCost = data.stream().map(StockReceiptOutReport::getOutCost).reduce(BigDecimal.ZERO, BigDecimal::add);
            minInventory = data.stream().mapToDouble(StockReceiptOutReport::getMinInventory).sum();
            cost = data.stream().map(StockReceiptOutReport::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
            res.setReceiptInventoryNum(reNum).setReceiptCost(reCost).setOutInventoryNum(outNum).
                    setOutCost(outCost).setMinInventory(minInventory).setCost(cost).setMeterUnit("合计: ");
            data.add(res);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", listMap);
        map.put("len", listMap == null ? 0 : listMap.size());
        map.put("sum", sum);

        return map;
    }

}
