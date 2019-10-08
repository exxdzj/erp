package com.exx.dzj.common.export;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.exx.dzj.entity.bean.StockInfoQuery;
import com.exx.dzj.entity.statistics.warehouse.StockReceiptOutReport;
import com.exx.dzj.util.DateUtil;
import com.exx.dzj.util.excel.export.model.StockReceiptOutInventoryModel;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import javax.servlet.ServletOutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName WareHouseExportUtils
 * @Description:
 * @Author yangyun
 * @Date 2019/10/8 0008 9:23
 * @Version 1.0
 **/
public class WareHouseExportUtils extends ExportUtil {

    public static ExcelWriter exportReceiptOutInventory(ServletOutputStream outputStream, Map<String, Object> map, String realName, StockInfoQuery query){
        ExcelWriter writer = null;
        try {
            writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

            String sheetName = "存货收发汇总表";

            Map<Integer, Integer> mapStyle = new HashMap<>();

            for (int i = 0; i < StockReceiptOutInventoryModel.class.getDeclaredFields().length; i++) {
                if (i == 0){
                    mapStyle.put(i, 8000);
                }
                mapStyle.put(i, 4000);
            }

            Sheet sheet = gainSheet(mapStyle, sheetName);
            sheet.setSheetName(sheetName);

            Table title = new Table(1);
            title.setClazz(StockReceiptOutInventoryModel.class);
            writer.write(null, sheet, title);

            List<StockReceiptOutInventoryModel> data1 = new ArrayList<>();
            StockReceiptOutInventoryModel model = new StockReceiptOutInventoryModel();
            model.setStockClassName("日期由: " + query.getStartDate());
            model.setStockCode("至: " + query.getEndDate());
            data1.add(model);
            writer.write(data1, sheet);

            model = new StockReceiptOutInventoryModel();
            model.setStockClassName("存货编号由: " + query.getFromId());
            model.setStockCode("至: " + query.getToId());
            data1 = new ArrayList<>();
            data1.add(model);
            writer.write(data1, sheet);

            int len = (int) map.get("len");
            if (len <= 0) {
                return writer;
            }

            LinkedHashMap<String, List<StockReceiptOutReport>> data = (LinkedHashMap<String, List<StockReceiptOutReport>>) map.get("data");
            Set<String> strings = data.keySet();
            List<StockReceiptOutReport> listData = null;

            StockReceiptOutReport temp = null;

            List<StockReceiptOutInventoryModel> content = new ArrayList<>();

            // body
            for (String key : strings) {
                model = new StockReceiptOutInventoryModel();
                listData = data.get(key);

                for (int i = 0; i < listData.size(); i++) {
                    temp = listData.get(i);
                    if (i == 0) {
                        model.setStockClassName(key);
                        setData(model, temp);
                    } else {
                        model = new StockReceiptOutInventoryModel();
                        setData(model, temp);
                    }

                    content.add(model);
                }
            }


            // 总计
            temp = (StockReceiptOutReport) map.get("sum");
            model = new StockReceiptOutInventoryModel();

            setData(model, temp);
            content.add(model);

            model = new StockReceiptOutInventoryModel();
            model.setStockClassName("公司名称: 正诚文化");
            model.setStockName("操作员：" + realName);
            model.setStockAddress("打印日期: "+DateUtil.getDate(new Date()));
            content.add(model);
            writer.write(content, sheet);
        } catch (Exception e){
            e.printStackTrace();
        }
        return writer;
    }

    private static void setData (StockReceiptOutInventoryModel model, StockReceiptOutReport temp){
        model.setStockCode(temp.getStockCode());
        model.setStockName(temp.getStockName());
        model.setMeterUnit(temp.getMeterUnit());
        model.setStockAddress(temp.getStockAddress());
        model.setBeginningMinInventory(temp.getBeginningMinInventory());
        model.setMinInventory(temp.getMinInventory());
        model.setOutInventoryNum(temp.getOutInventoryNum());
        model.setReceiptInventoryNum(temp.getReceiptInventoryNum());

        model.setBeginningPrice(resovel(temp.getBeginningPrice()));
        model.setBeginningCost(resovel(temp.getBeginningCost()));
        model.setInAvgPrice(resovel(temp.getAvgPrice()));
        model.setReceiptCost(resovel(temp.getReceiptCost()));
        model.setOutAvgPrice(resovel(temp.getAvgPrice()));
        model.setOutCost(resovel(temp.getOutCost()));
        model.setAvgPrice(resovel(temp.getAvgPrice()));
        model.setCost(resovel(temp.getCost()));

//        model.setBeginningPrice(temp.getBeginningPrice().doubleValue());
//        model.setBeginningCost(temp.getBeginningCost().doubleValue());
//        model.setInAvgPrice(temp.getAvgPrice().doubleValue());
//        model.setReceiptCost(temp.getReceiptCost().doubleValue());
//        model.setOutAvgPrice(temp.getAvgPrice().doubleValue());
//        model.setOutCost(temp.getOutCost().doubleValue());
//        model.setAvgPrice(temp.getAvgPrice().doubleValue());
//        model.setCost(temp.getCost().doubleValue());
    }

    public static String resovel(BigDecimal b){
        if (b == null){
            return "";
        }
        return b.toString();
    }
}
