package com.exx.dzj.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class SaleGoodsModel extends BaseRowModel {

    @ExcelProperty(value = "日期", index = 0, format = "yyyy-MM-dd")
    private Timestamp saleDate;
    @ExcelProperty(value = "单据号", index = 1)
    private String saleCode;
    @ExcelProperty(value = "业务类型", index = 2)
    private String type;

    @ExcelProperty(value = "客户", index = 3)
    private String cust;

    @ExcelProperty(value = "销售员", index = 4)
    private String salesman;

    @ExcelProperty(value = "存货", index = 5)
    private String stockCode;
    @ExcelProperty(value = "地点", index = 6)
    private String stockAddress;
    @ExcelProperty(value = "销售类别", index = 7)
    private String saleType;
    @ExcelProperty(value = "数量", index = 8)
    private Double goodsNum;
    @ExcelProperty(value = "单价", index = 9)
    private BigDecimal unitPrice;
    @ExcelProperty(value = "原币销售额", index = 10)
    private BigDecimal salesVolume;
}
