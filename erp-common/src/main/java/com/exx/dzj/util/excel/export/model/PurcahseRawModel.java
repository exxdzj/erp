package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @ClassName PurcahseRawModel
 * @Description:
 * @Author yangyun
 * @Date 2019/8/13 0013 11:26
 * @Version 1.0
 **/
@Data
public class PurcahseRawModel extends BaseRowModel {
    @ExcelProperty(value = "编号", index = 0)
    private String purchaseCode;
    @ExcelProperty(value = "日期", index = 1, format = "yyyy-MM-dd")
    private Timestamp purhcaseDate;
    @ExcelProperty(value = "编号", index = 2)
    private String supplierName;
    @ExcelProperty(value = "编号", index = 3)
    private String currencyAndRate;
    @ExcelProperty(value = "编号", index = 4)
    private double purchaseVolume;
    @ExcelProperty(value = "编号", index = 5)
    private Timestamp dueDate;
    @ExcelProperty(value = "编号", index = 6)
    private String status;
    @ExcelProperty(value = "编号", index = 7)
    private String invoiceCode;
    @ExcelProperty(value = "编号", index = 8)
    private String purchaseName;
    @ExcelProperty(value = "编号", index = 9)
    private String createUser;
    @ExcelProperty(value = "编号", index = 10)
    private String remark;
}
