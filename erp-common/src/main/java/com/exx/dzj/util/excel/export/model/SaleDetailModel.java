package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author yangyun
 * @create 2019-06-26-16:50
 */
@Data
public class SaleDetailModel extends BaseRowModel {

    @ExcelProperty(value = {"销售明细表","销售明细表", "日期"}, index = 0)
    private String saleDate;
    @ExcelProperty(value = {"销售明细表","销售明细表", "单据号"}, index = 1)
    private String saleCode;
    @ExcelProperty(value = {"销售明细表","销售明细表", "客户"}, index = 2)
    private String customer;
    @ExcelProperty(value = {"销售明细表","销售明细表", "销售员"}, index = 3)
    private String salesman;
    @ExcelProperty(value = {"销售明细表","销售明细表", "存货"}, index = 4)
    private String stockName;
    @ExcelProperty(value = {"销售明细表","销售明细表", "地点"}, index = 5)
    private String stockAddress;
    @ExcelProperty(value = {"销售明细表","销售明细表", "数量"}, index = 6)
    private String goodsNum;
    @ExcelProperty(value = {"销售明细表","销售明细表", "单价"}, index = 7)
    private String unitPrice;
    @ExcelProperty(value = {"销售明细表","销售明细表", "原币销售额"}, index = 8)
    private String saleVolume;
}
