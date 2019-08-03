package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author yangyun
 * @create 2019-04-30-10:18
 */
@Data
public class SaleDeductionModel extends BaseRowModel {

    @ExcelProperty(value = {"销售提成汇总分析报表", "销售提成汇总分析报表", "销售人员"}, index = 0)
    private String saleMan;

    @ExcelProperty(value = {"销售提成汇总分析报表", "销售提成汇总分析报表", "销售数量"}, index = 1)
    private Double saleGoodsNum;

    @ExcelProperty(value = {"销售提成汇总分析报表", "销售提成汇总分析报表", "销售收入"}, index = 2)
    private Double saleVolume;

    @ExcelProperty(value = {"销售提成汇总分析报表", "销售提成汇总分析报表", "销售成本"}, index = 3)
    private Double saleCost;

    @ExcelProperty(value = {"销售提成汇总分析报表", "销售提成汇总分析报表", "毛利"}, index = 4)
    private Double grossMargin;

    @ExcelProperty(value = {"销售提成汇总分析报表", "销售提成汇总分析报表", "毛利率(%)"}, index = 5)
    private Double grossRate;

    @ExcelProperty(value = {"销售提成汇总分析报表", "销售提成汇总分析报表", "费用"}, index = 6)
    private Double fee;

    @ExcelProperty(value = {"销售提成汇总分析报表", "销售提成汇总分析报表", "纯利"}, index = 7)
    private Double pureProfit;

    @ExcelProperty(value = {"销售提成汇总分析报表", "销售提成汇总分析报表", "佣金率"}, index = 8)
    private String commissionRate;

    @ExcelProperty(value = {"销售提成汇总分析报表", "销售提成汇总分析报表", "佣金"}, index = 9)
    private Double commission;
}
