package com.exx.dzj.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yangyun
 * @create 2019-03-27-15:38
 */
@Data
public class StockModel extends BaseRowModel {
    @ExcelProperty(value = "存货编号", index = 0)
    private String stockCode;

    @ExcelProperty(value = "存货性质", index = 1) //
    private String nature;

    @ExcelProperty(value = "存货名称", index = 2)
    private String stockName;

    @ExcelProperty(value = "规格型号", index = 3)
    private String specificateType;

    @ExcelProperty(value = "存货类别", index = 4)
    private String stockClass;

    @ExcelProperty(value = "默认存货地点", index = 5)
    private String stockAddress;

    @ExcelProperty(value = "计量单位", index = 6)
    private String meterUnit;

    @ExcelProperty(value = "条形码", index = 7)
    private String barCode;

    @ExcelProperty(value = "标准买价", index = 8)
    private BigDecimal standardBuyPrice;

    @ExcelProperty(value = "标准卖价", index = 9)
    private BigDecimal standardSellPrice;

    @ExcelProperty(value = "最高采购限价", index = 10)
    private BigDecimal maxPurchasePrice;

    @ExcelProperty(value = "最低销售限价", index = 11)
    private BigDecimal minSellPrice;

    @ExcelProperty(value = "最低存量", index = 12)
    private Integer minInventory;

    @ExcelProperty(value = "状态", index = 12) // 1 使用中 0 禁用
    private String status;
}
