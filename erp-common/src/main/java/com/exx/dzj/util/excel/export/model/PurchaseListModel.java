package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @ClassName PurchaseListModel
 * @Description:
 * @Author yangyun
 * @Date 2019/8/9 0009 14:24
 * @Version 1.0
 **/
@Data
public class PurchaseListModel extends BaseRowModel {
    @ExcelProperty(value = "日期", index = 0, format = "yyyy-MM-dd")
    private Timestamp purchaseDate;
    @ExcelProperty(value = "编号", index = 1)
    private String purchaseCode;
    @ExcelProperty(value = "状态", index = 2)
    private String paymentStatus;
    @ExcelProperty(value = "送货地址", index = 3)
    private String deliveryAddress;
    @ExcelProperty(value = "采购人员", index = 4)
    private String salesmanName;
    @ExcelProperty(value = "项目", index = 5)
    private String purchaseProject;
    @ExcelProperty(value = "采购订单号", index = 6)
    private String purchaseOrderCode;
    @ExcelProperty(value = "发票编号", index = 7)
    private String invoiceCode;
    @ExcelProperty(value = "备注", index = 8)
    private String purchaseRemark;
    @ExcelProperty(value = "优惠", index = 9)
    private double discountAmount;
    @ExcelProperty(value = "采购额", index = 10)
    private double purchaseSumVolume;
    @ExcelProperty(value = "已付款", index = 11)
    private double sumCollectedAmount;
    @ExcelProperty(value = "审核状态", index = 12)
    private String flowStatus;
    @ExcelProperty(value = "付款条件", index = 13)
    private String collectionTerms;
    @ExcelProperty(value = "账期", index = 14)
    private Integer accountPeriod;
    @ExcelProperty(value = "付款人", index = 15)
    private String createUser;
    @ExcelProperty(value = "供应商", index = 16)
    private String custName;
    @ExcelProperty(value = "供应商电话", index = 17)
    private String custPhoneNum;

    @ExcelProperty(value = "本次付款", index = 18)
    private double collectedAmount;
    @ExcelProperty(value = "现金银行账户", index = 19)
    private String collectionAccount;
    @ExcelProperty(value = "付款方式", index = 20)
    private String paymentMethod;

    @ExcelProperty(value = "存货或服务", index = 21)
    private String stockCode;
    @ExcelProperty(value = "存货名称", index = 22)
    private String stockName;
    @ExcelProperty(value = "存货地点", index = 23)
    private String stockAddress;
    @ExcelProperty(value = "数量", index = 24)
    private Double goodsNum;

    @ExcelProperty(value = "单价", index = 25)
    private double realSellPrice;
    @ExcelProperty(value = "采购额", index = 26)
    private double purchaseVolume;
    @ExcelProperty(value = "说明", index = 27)
    private String goodsRemark;
}
