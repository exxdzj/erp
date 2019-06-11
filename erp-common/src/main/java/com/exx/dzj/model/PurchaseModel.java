package com.exx.dzj.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangyun
 * @create 2019-06-10-10:51
 */
@Data
public class PurchaseModel extends BaseRowModel {

    @ExcelProperty(value = "日期", index = 0, format = "yyyy-MM-dd")
    private Date purchaseDate;

    @ExcelProperty(value = "编号", index = 1)
    private String purchaseCode;

    @ExcelProperty(value = "状态", index = 2)
    private String paymentStatus;

    @ExcelProperty(value = "供应商", index = 3)
    private String custCode;

    @ExcelProperty(value = "币别", index = 4)
    private String currency;

    @ExcelProperty(value = "汇率", index = 5)
    private Double exchangeRate;

    @ExcelProperty(value = "送货地址", index = 6)
    private String deliveryAddress;

    @ExcelProperty(value = "采购人员", index = 7)
    private String userCode;

    @ExcelProperty(value = "项目", index = 8)
    private String purchaseProject;

    @ExcelProperty(value = "采购订单号", index = 9)
    private String purchaseOrderCode;

    @ExcelProperty(value = "收货单号", index = 10)
    private String deliveryOrderCode;

    @ExcelProperty(value = "发票编号", index = 11)
    private String invoiceCode;

    @ExcelProperty(value = "备注", index = 13)
    private String remarks;

    @ExcelProperty(value = "优惠", index = 14) // 优惠金额
    private BigDecimal discountAmount;

    // 付款信息
    @ExcelProperty(value = "本次付款", index = 15) //已付款
    private BigDecimal collectedAmount;

    @ExcelProperty(value = "现金银行账户", index = 16)
    private String collectionAccount;

    @ExcelProperty(value = "付款方式", index = 17)
    private String paymentMethod;
    // 付款信息

    @ExcelProperty(value = "付款条件", index = 18)
    private String collectionTerms;

    @ExcelProperty(value = "账期", index = 19)
    private Integer accountPeriod;

    @ExcelProperty(value = "付款单号", index = 20)
    private String receiptNum;

    @ExcelProperty(value = "付款人", index = 21)
    private String createUser;

    @ExcelProperty(value = "存货或服务", index = 22)
    private String stockCode;

    @ExcelProperty(value = "存货名称", index = 23)
    private String stockName;

    @ExcelProperty(value = "存货型号", index = 24)
    private String specificateType;

    @ExcelProperty(value = "地点", index = 25)
    private String stockAddress;

    @ExcelProperty(value = "数量", index = 26)
    private Double goodsNum;

    @ExcelProperty(value = "单价", index = 27)
    private BigDecimal unitPrice;

    @ExcelProperty(value = "折扣率%", index = 28)
    private BigDecimal discountRate;

    @ExcelProperty(value = "折扣额", index = 29)
    private BigDecimal goodsDiscountAmount;

    @ExcelProperty(value = "采购额", index = 32)
    private BigDecimal purchaseVolume;

//    @ExcelProperty(value = "明细金额", index = 33)
//    private BigDecimal receivableAccoun;

    @ExcelProperty(value = "说明", index = 34)
    private String goodsRemark;


}
