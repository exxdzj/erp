package com.exx.dzj.util.excel.export.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author yangyun
 * @create 2019-06-15-10:40
 */
@Data
public class SaleListTenetModel extends BaseRowModel {

    @ExcelProperty(value = "销售单编码", index = 0)
    private String saleCode;

    @ExcelProperty(value = "日期", index = 1, format = "yyyy-MM-dd")
    private Timestamp saleDate;

    @ExcelProperty(value = "客户编码", index = 2)
    private String custCode;

    @ExcelProperty(value = "客户名称", index = 3)
    private String custName;

    @ExcelProperty(value = "客户电话", index = 4)
    private String custPhoneNum;

    @ExcelProperty(value = "币别", index = 5)
    private String currency;

    @ExcelProperty(value = "汇率", index = 6)
    private String exchangeRate;

    @ExcelProperty(value = "总金额", index = 7)
    private String receivableAccoun;

    @ExcelProperty(value = "已收款金额", index = 8)
    private String sumCollectedAmount;

    @ExcelProperty(value = "未收款金额", index = 9)
    private String unCollectedAmount;

    @ExcelProperty(value = "状态", index = 10)
    private String paymentStatus;

    @ExcelProperty(value = "是否签收", index = 11)
    private String isReceipt;

    @ExcelProperty(value = "销售员", index = 12)
    private String salesmanName;

    @ExcelProperty(value = "制单人", index = 13)
    private String collectionUserName;

    @ExcelProperty(value = "备注", index = 14)
    private String saleRemark;

    @ExcelProperty(value = "送货地址", index = 15)
    private String deliveryAddress;

    @ExcelProperty(value = "物流名称", index = 16)
    private String logisticsCompamyName;
    @ExcelProperty(value = "快递单号", index = 17)
    private String freihtCode;
    @ExcelProperty(value = "发货时间", index = 18)
    private String deliverGoodsTime;
    @ExcelProperty(value = "查询电话", index = 19)
    private String selectorPhoneNum;
    @ExcelProperty(value = "物流备注", index = 20)
    private String remark;



    @ExcelProperty(value = "项目", index = 21)
    private String saleProject;

    @ExcelProperty(value = "优惠", index = 22)
    private String discountAmount;

    @ExcelProperty(value = "收款条件", index = 23)
    private String collectionTerms;

    @ExcelProperty(value = "账期", index = 24)
    private String accountPeriod;

//    @ExcelProperty(value = "已收款", index = 25)
//    private String sumCollectedAmount;

    @ExcelProperty(value = "所属分公司", index = 25)
    private String subordinateCompanyName;
}
