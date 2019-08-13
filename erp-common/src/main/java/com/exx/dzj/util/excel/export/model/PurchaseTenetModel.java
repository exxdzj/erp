package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @ClassName PurchaseTenetModel
 * @Description:
 * @Author yangyun
 * @Date 2019/8/9 0009 15:34
 * @Version 1.0
 **/
@Data
public class PurchaseTenetModel {
    @ExcelProperty(value = "采购单编号", index = 0)
    private String purchaseCode;
    @ExcelProperty(value = "日期", index = 1, format = "yyyy-MM-dd")
    private Timestamp purchaseDate;
    @ExcelProperty(value = "供应商", index = 2)
    private String custName;
    @ExcelProperty(value = "供应商电话", index = 3)
    private String custPhoneNum;
    @ExcelProperty(value = "采购额", index = 4)
    private BigDecimal purchaseSumVolume;
    @ExcelProperty(value = "状态", index = 5)
    private String paymentStatus;
    @ExcelProperty(value = "发票编号", index = 6)
    private String invoiceCode;
    @ExcelProperty(value = "采购人员", index = 7)
    private String salesmanName;
    @ExcelProperty(value = "制单人", index = 8)
    private String createUser;
    @ExcelProperty(value = "备注", index = 9)
    private String purchaseRemark;
}
