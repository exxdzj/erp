package com.exx.dzj.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangyun
 * @create 2019-03-19-11:02
 */
@Data
@ToString
public class SaleModel extends BaseRowModel {

    @ExcelProperty(value = "日期", index = 0, format = "yyyy-MM-dd")
    private Date saleDate;

    @ExcelProperty(value = "编号", index = 1)
    private String saleCode;

    @ExcelProperty(value = "状态", index = 2)
    private String paymentStatus;

    @ExcelProperty(value = "客户", index = 3)
    private String custCode;

    @ExcelProperty(value = "币别", index = 4)
    private String currency;

    @ExcelProperty(value = "汇率", index = 5)
    private Double exchangeRate;

    @ExcelProperty(value = "销售人员", index = 6)
    private String userCode;

    @ExcelProperty(value = "项目", index = 7)
    private String saleProject;

    @ExcelProperty(value = "销售订单号", index = 8)
    private String salesOrderCode;

    @ExcelProperty(value = "送货订单号", index = 9)
    private String deliveryOrderCode;

    @ExcelProperty(value = "送货地址", index = 10)
    private String deliveryAddress;

    @ExcelProperty(value = "客户订单号", index = 11)
    private String custOrderCode;

    @ExcelProperty(value = "备注", index = 12)
    private String remarks;

    @ExcelProperty(value = "发票编号", index = 13)
    private String invoiceCode;

    @ExcelProperty(value = "优惠", index = 14)
    private BigDecimal discountAmount;

    @ExcelProperty(value = "本次收款", index = 15)
    private BigDecimal collectedAmount;

    @ExcelProperty(value = "现金银行账户", index = 16)
    private String collectionAccount;

    @ExcelProperty(value = "收款方式", index = 17)
    private String paymentTerm;

    @ExcelProperty(value = "收款条件", index = 18)
    private String collectionTerms;

    @ExcelProperty(value = "账期", index = 19)
    private Integer accountPeriod;

    @ExcelProperty(value = "收款单号", index = 20)
    private String receiptNum;

    @ExcelProperty(value = "收款人", index = 21)
    private String collectionUserName;

    @ExcelProperty(value = "到期日", index = 22, format = "yyyy-MM-dd")
    private Date dueDate;

    @ExcelProperty(value = "存货或服务", index = 23)
    private String stockCode;

    @ExcelProperty(value = "存货名称", index = 24)
    private String stockName;

    @ExcelProperty(value = "存货型号", index = 25)
    private String specificateType;

    @ExcelProperty(value = "地点", index = 26)
    private String stockAddress;

    @ExcelProperty(value = "数量", index = 27)
    private Double goodsNum;

    @ExcelProperty(value = "单价", index = 28)
    private BigDecimal unitPrice;

    @ExcelProperty(value = "折扣率%", index = 29)
    private BigDecimal discountRate;

    @ExcelProperty(value = "折扣额", index = 30)
    private BigDecimal goodsDiscountAmount;

    @ExcelProperty(value = "销售额", index = 31)
    private BigDecimal salesVolume;

//    @ExcelProperty(value = "税率%", index = 32)
//    private

//    @ExcelProperty(value = "税额", index = 33)
//    private

    @ExcelProperty(value = "明细金额", index = 34)
    private BigDecimal receivableAccoun;

    @ExcelProperty(value = "说明", index = 35)
    private String goodsRemark;

    @ExcelProperty(value = "销售订单号", index = 36)
    private String saleCodes;
}
