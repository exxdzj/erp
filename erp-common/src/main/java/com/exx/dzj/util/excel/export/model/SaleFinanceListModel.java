package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author yangyun
 * @create 2019-06-14-10:05
 */
@Data
public class SaleFinanceListModel extends BaseRowModel {
    @ExcelProperty(value = "日期", index = 0, format = "yyyy-MM-dd")
    private Timestamp saleDate;

    @ExcelProperty(value = "状态", index = 1)
    private String paymentStatus;

    @ExcelProperty(value = "客户", index = 2)
    private String custName ;

    @ExcelProperty(value = "备注", index = 3)
    private String saleRemark;

    /********商品信息******/

    @ExcelProperty(value = "存货服务", index = 4)
    private String stockName;

    @ExcelProperty(value = "数量", index = 5)
    private double goodsNum;

    @ExcelProperty(value = "单价", index = 6)
    private double unitPrice;

    @ExcelProperty(value = "销售额", index = 7)
    private double salesVolume;


    @ExcelProperty(value = "说明", index = 8)
    private String goodsRemark;
    /******商品信息******/

}
