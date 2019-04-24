package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @description
 * @author yangyun
 * @date 2019/4/23 0023
 * @param null
 * @return
 */
@Data
public class CustomerSaleModel extends BaseRowModel {

    @ExcelProperty(value = {"销售分析-依客户", "销售分析-依客户","建单日期"}, index = 0)
    private String createTime;

    @ExcelProperty(value = {"销售分析-依客户", "销售分析-依客户","销售单据号"}, index = 1)
    private String saleCode;

    @ExcelProperty(value = {"销售分析-依客户", "销售分析-依客户","存货名称"}, index = 2)
    private String stockName;

    @ExcelProperty(value = {"销售分析-依客户", "销售分析-依客户","数量"}, index = 3)
    private String goodsNum;

    @ExcelProperty(value = {"销售分析-依客户", "销售分析-依客户","单价"}, index = 4)
    private String unitPrice;

    @ExcelProperty(value = {"销售分析-依客户", "销售分析-依客户","销售额"}, index = 5)
    private String salesVolume;

    @ExcelProperty(value = {"销售分析-依客户", "销售分析-依客户","单位成本"}, index = 6)
    private String cost;

    @ExcelProperty(value = {"销售分析-依客户", "销售分析-依客户","总成本"}, index = 7)
    private String sumCost;

    @ExcelProperty(value = {"销售分析-依客户", "销售分析-依客户","毛利"}, index = 8)
    private String grossMargin;

    @ExcelProperty(value = {"销售分析-依客户", "销售分析-依客户","毛利率"}, index = 9)
    private String grossRate;
}
