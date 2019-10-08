package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @ClassName StockReceiptOutInventoryModel
 * @Description:
 * @Author yangyun
 * @Date 2019/10/8 0008 9:06
 * @Version 1.0
 **/
@Data
public class StockReceiptOutInventoryModel extends BaseRowModel {

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","存货类别"}, index = 0)
    private String stockClassName;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","存货编号"}, index = 1)
    private String stockCode;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","存货名称"}, index = 2)
    private String stockName;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","存货地点"}, index = 3)
    private String stockAddress;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","单位"}, index = 4)
    private String meterUnit;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","期初数量"}, index = 5)
    private Double beginningMinInventory;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","期初单位成本"}, index = 6)
    private String beginningPrice;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","期初成本"}, index = 7)
    private String beginningCost;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","入库数量"}, index = 8)
    private Double receiptInventoryNum;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","入库单位成本"}, index = 9)
    private String inAvgPrice;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","入库成本"}, index = 10)
    private String receiptCost;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","出库数量"}, index = 11)
    private Double outInventoryNum;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","出库单位成本"}, index = 12)
    private String outAvgPrice;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","出库成本"}, index = 13)
    private String outCost;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","结存数量"}, index = 14)
    private Double minInventory;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","结存单位成本"}, index = 15)
    private String avgPrice;

    @ExcelProperty(value = {"存货收发汇总表", "存货收发汇总表","结存成本"}, index = 16)
    private String cost;
}
