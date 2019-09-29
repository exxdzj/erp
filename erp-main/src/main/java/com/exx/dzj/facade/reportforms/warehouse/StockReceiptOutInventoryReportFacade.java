package com.exx.dzj.facade.reportforms.warehouse;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.statistics.warehouse.StockReceiptOutReport;
import com.exx.dzj.service.statistics.warehouse.StockReceiptOutInventoryReportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
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

        String startDate = query.getStartDate();

        BigDecimal beginningCost = BigDecimal.ZERO;

        for (StockReceiptOutReport temp : colloct){
            if (StringUtils.isBlank(startDate)){
                temp.setBeginningCost(BigDecimal.ZERO).setBeginningMinInventory(Double.valueOf(CommonConstant.DEFAULT_VALUE_ZERO))
                        .setBeginningPrice(BigDecimal.ZERO);
            } else {
                beginningCost = temp.getAvgPrice().multiply(new BigDecimal(temp.getBeginningMinInventory()));
                temp.setBeginningCost(beginningCost);
                temp.setBeginningPrice(temp.getAvgPrice());
            }
        }

        LinkedHashMap<String, List<StockReceiptOutReport>> linkedMap = new LinkedHashMap<>();

        List<StockReceiptOutReport> list = null;
        for (StockReceiptOutReport temp: colloct){
            list = linkedMap.get(temp.getStockClassName()) == null ? null : linkedMap.get(temp.getStockClassName());
            if (list == null){
                list = new ArrayList<>();
                linkedMap.put(temp.getStockClassName(), list);
            }
            list.add(temp);

        }

        StockReceiptOutReport sum = new StockReceiptOutReport();
        double sumReNum = colloct.stream().mapToDouble(StockReceiptOutReport::getReceiptInventoryNum).sum();
        BigDecimal sumReCost = colloct.stream().map(StockReceiptOutReport::getReceiptCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        double sumOutInventoryNum = colloct.stream().mapToDouble(StockReceiptOutReport::getOutInventoryNum).sum();
        BigDecimal sumOutCost = colloct.stream().map(StockReceiptOutReport::getOutCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        double sumMinInventory = colloct.stream().mapToDouble(StockReceiptOutReport::getMinInventory).sum();
        BigDecimal sumCost = colloct.stream().map(StockReceiptOutReport::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);

        sum.setReceiptInventoryNum(sumReNum).setReceiptCost(sumReCost).setOutInventoryNum(sumOutInventoryNum).
                setOutCost(sumOutCost).setMinInventory(sumMinInventory).setCost(sumCost).setMeterUnit("总计: ");


        Collection<List<StockReceiptOutReport>> values = linkedMap.values();
        double reNum = 0.0;
        double outNum = 0.0;
        double minInventory = 0.0;
        BigDecimal reCost = BigDecimal.ZERO;
        BigDecimal outCost = BigDecimal.ZERO;
        BigDecimal cost = BigDecimal.ZERO;
        double beginningSumNum = 0.0; // 期初总计数量
        BigDecimal beginningSumCost = BigDecimal.ZERO; // 期初成本总计
        StockReceiptOutReport res = null;
        for (List<StockReceiptOutReport> data : values){
            res = new StockReceiptOutReport();
            reNum = data.stream().mapToDouble(StockReceiptOutReport::getReceiptInventoryNum).sum();
            reCost = data.stream().map(StockReceiptOutReport::getReceiptCost).reduce(BigDecimal.ZERO, BigDecimal::add);
            outNum = data.stream().mapToDouble(StockReceiptOutReport::getOutInventoryNum).sum();
            outCost = data.stream().map(StockReceiptOutReport::getOutCost).reduce(BigDecimal.ZERO, BigDecimal::add);
            minInventory = data.stream().mapToDouble(StockReceiptOutReport::getMinInventory).sum();
            cost = data.stream().map(StockReceiptOutReport::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
            beginningSumNum = data.stream().mapToDouble(StockReceiptOutReport::getBeginningMinInventory).sum();
            beginningSumCost = data.stream().map(StockReceiptOutReport::getBeginningCost).reduce(BigDecimal.ZERO, BigDecimal::add);
            res.setReceiptInventoryNum(reNum).setReceiptCost(reCost).setOutInventoryNum(outNum).setBeginningMinInventory(beginningSumNum)
                    .setBeginningCost(beginningSumCost).setOutCost(outCost).setMinInventory(minInventory).setCost(cost).setMeterUnit("合计: ");
            data.add(res);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", linkedMap);
        map.put("len", linkedMap == null ? 0 : linkedMap.size());
        map.put("sum", sum);

        return map;
    }

}
