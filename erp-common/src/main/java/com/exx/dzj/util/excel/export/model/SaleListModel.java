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
public class SaleListModel extends BaseRowModel {
    @ExcelProperty(value = "编号", index = 0)
    private String saleCode;

    @ExcelProperty(value = "日期", index = 1, format = "yyyy-MM-dd")
    private Timestamp saleDate;

    @ExcelProperty(value = "状态", index = 2)
    private String paymentStatus;

    @ExcelProperty(value = "客户", index = 3)
    private String custName ;

    @ExcelProperty(value = "客户电话", index = 4)
    private String custPhoneNum;

    @ExcelProperty(value = "币别", index = 5)
    private String currency ;

    @ExcelProperty(value = "汇率", index = 6)
    private String exchangeRate ;

    @ExcelProperty(value = "销售员", index = 7)
    private String salesmanName;

    @ExcelProperty(value = "项目", index = 8)
    private String saleProject;

    @ExcelProperty(value = "送货地址", index = 9)
    private String deliveryAddress;

    @ExcelProperty(value = "备注", index = 10)
    private String saleRemark;

    @ExcelProperty(value = "是否已签收", index = 11)
    private String isReceipt;

    @ExcelProperty(value = "所属公司", index = 12)
    private String subordinateCompanyName;

    @ExcelProperty(value = "优惠金额", index = 13)
    private String discountAmount;

    @ExcelProperty(value = "账期", index = 14)
    private String accountPeriod;

    @ExcelProperty(value = "收款人", index = 15)
    private String collectionUserName;

    @ExcelProperty(value = "到期日", index = 16, format = "yyyy-MM-dd")
    private Timestamp dueDate;
    @ExcelProperty(value = "收款条件", index = 17)
    private String collectionTerms;

    /***收款信息**/
    @ExcelProperty(value = "本次收款", index = 18)
    private String collectedAmount;

    @ExcelProperty(value = "收款账户", index = 19)
    private String collectionAccount;

    @ExcelProperty(value = "收款方式", index = 20)
    private String paymentMethod;

    /***收款信息**/

    /********商品信息******/

    @ExcelProperty(value = "存货服务", index = 21)
    private String stockName;

    @ExcelProperty(value = "地点", index = 22)
    private String stockAddress;

    @ExcelProperty(value = "数量", index = 23)
    private String goodsNum;

    @ExcelProperty(value = "单价", index = 24)
    private String unitPrice;

    @ExcelProperty(value = "折扣率%", index = 25)
    private String discountRate;

    @ExcelProperty(value = "折扣额", index = 26)
    private String goodsDiscountAmount;

    @ExcelProperty(value = "销售额", index = 27)
    private String salesVolume;

    @ExcelProperty(value = "说明", index = 28)
    private String goodsRemark;

    /******商品信息******/

}
