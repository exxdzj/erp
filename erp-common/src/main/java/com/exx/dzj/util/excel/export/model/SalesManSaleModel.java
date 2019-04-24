package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author yangyun
 * @create 2019-04-20-17:47
 */
@Data
public class SalesManSaleModel extends BaseRowModel {

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","建单日期"}, index = 0)
    private String createTime;

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","销售单据号"}, index = 1)
    private String saleCode;

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","客户"}, index = 2)
    private String custName;

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","回款额"}, index = 3)
    private String backAmount;

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","存货名称"}, index = 4)
    private String stockName;

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","数量"}, index = 5)
    private String goodsNum;

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","单价"}, index =6)
    private String unitPrice;

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","销售额"}, index = 6)
    private String salesVolume;

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","单位成本"}, index = 7)
    private String cost;

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","总成本"}, index = 8)
    private String sumCost;

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","毛利"}, index = 10)
    private String grossMargin;

    @ExcelProperty(value = {"销售分析-依销售人员", "销售分析-依销售人员","毛利率"}, index = 11)
    private String grossRate;
}
