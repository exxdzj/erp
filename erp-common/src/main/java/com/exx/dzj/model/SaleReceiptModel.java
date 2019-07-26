package com.exx.dzj.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class SaleReceiptModel extends BaseRowModel {

    @ExcelProperty(value = "客户编号", index = 0)
    private String custCode;
    @ExcelProperty(value = "客户名称", index = 1)
    private String custName;
    @ExcelProperty(value = "单号", index = 2)
    private String saleCode;
    @ExcelProperty(value = "日期", index = 3, format = "yyyy-MM-dd")
    private Timestamp saleDate;
    @ExcelProperty(value = "类型", index = 4)
    private String type;
    @ExcelProperty(value = "金额", index = 5) // 优惠后金额
    private BigDecimal receivableAccoun;
    @ExcelProperty(value = "本次结算", index = 6) // 已收款
    private BigDecimal collectedAmount;
    @ExcelProperty(value = "应收款余额", index = 7) // 应收款金额
    private BigDecimal yue;
//    @ExcelProperty(value = "回款率", index = 8)

}
