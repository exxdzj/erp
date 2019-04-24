package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangyun
 * @create 2019-04-16-16:32
 */
@Data
public class InventorySaleModel extends BaseRowModel {
    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","存货类别"}, index = 0)
    private String stockTypeName;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货",""}, index = 1)
    private String vacant;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","销售单据号"}, index = 2)
    private String saleCode;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","销售员"}, index = 3)
    private String realName;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","建单日期"}, index = 4, format = "yyyy-MM-dd")
    private String createTime;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","存货地点"}, index = 5)
    private String stockAddress;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","客户"}, index = 6)
    private String custName;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","数量"}, index = 7)
    private Double goodsNum;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","单价"}, index = 8)
    private BigDecimal unitPrice;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","销售额"}, index = 9)
    private BigDecimal salesVolume;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","单位成本"}, index = 10)
    private BigDecimal standardBuyPrice;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","总成本"}, index = 11)
    private BigDecimal cost;

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","毛利"}, index = 12)
    private String grossMargin; // 毛利

    @ExcelProperty(value = {"销售分析-依存货", "销售分析-依存货","毛利率"}, index = 13)
    private String grossRate; // 毛利率
}
