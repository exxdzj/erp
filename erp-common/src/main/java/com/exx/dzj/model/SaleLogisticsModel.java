package com.exx.dzj.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SaleLogisticsModel extends BaseRowModel {
    @ExcelProperty(value = "销售单编号", index = 0)
    private String saleCode;
    @ExcelProperty(value = "日期", index = 1, format = "yyyy-MM-dd")
    private String saleDate;
    @ExcelProperty(value = "客户编码", index = 2)
    private String custCode;
    @ExcelProperty(value = "客户名称", index = 3)
    private String custName;
    @ExcelProperty(value = "币别", index = 4)
    private String a;
    @ExcelProperty(value = "汇率", index = 5)
    private String b;
    @ExcelProperty(value = "金额", index = 6)
    private String c;
    @ExcelProperty(value = "到期日", index = 7, format = "yyyy-MM-dd")
    private String d;
    @ExcelProperty(value = "状态", index = 8)
    private String e;
    @ExcelProperty(value = "货运单号", index = 9)
    private String remark;
    @ExcelProperty(value = "发票编号", index = 10)
    private String f;
    @ExcelProperty(value = "凭证号", index = 11)
    private String g;
    @ExcelProperty(value = "销售员", index = 12)
    private String h;
    @ExcelProperty(value = "制单人", index = 13)
    private String i;
    @ExcelProperty(value = "备注", index = 14)
    private String j;
    @ExcelProperty(value = "送货地址", index = 15)
    private String k;
    @ExcelProperty(value = "物流公司编号", index = 16)
    private String logisticsCompanyCode;
    @ExcelProperty(value = "物流公司名称", index = 17)
    private String logisticsCompamyName;
    @ExcelProperty(value = "物流查询电话", index = 18)
    private String selectorPhoneNum;
    @ExcelProperty(value = "物流出货日期", index = 19, format = "yyyy-MM-dd")
    private String deliverGoodsTime;
}
