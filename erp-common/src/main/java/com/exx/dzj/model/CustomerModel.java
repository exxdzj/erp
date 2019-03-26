package com.exx.dzj.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 客户数据导入
 * @author yangyun
 * @date 2019/3/20 0020
 * @param null
 * @return
 */

@Data
@ToString
public class CustomerModel extends BaseRowModel {
    @ExcelProperty(value = "编号", index = 0)
    private String custCode;

    @ExcelProperty(value = "属性", index = 1)
    private String custType;

    @ExcelProperty(value = "名称", index = 2)
    private String custName;

    @ExcelProperty(value = "类别", index = 3)
    private String levelName;

    @ExcelProperty(value = "地区", index = 4)
    private String regionCodeName;

    @ExcelProperty(value = "备注", index = 5)
    private String remarks;

    @ExcelProperty(value = "联系人", index = 6)
    private String linkMan;

    @ExcelProperty(value = "电话号码", index = 7)
    private String telephoneNum;

    @ExcelProperty(value = "手机号码", index = 8)
    private String phoneNum;

    @ExcelProperty(value = "传真号码", index = 9)
    private String faxNum;

    @ExcelProperty(value = "E-Mail", index = 10)
    private String email;

    @ExcelProperty(value = "通讯地址（本地）", index = 11)
    private String postalAddress;

    @ExcelProperty(value = "通讯地址（英语）", index = 12)
    private String postalAddressEng;

    @ExcelProperty(value = "送货地址（本地）", index = 13)
    private String deliveryAddressOne;

    @ExcelProperty(value = "送货地址（英语）", index = 14)
    private String deliveryAddressTwo;

    @ExcelProperty(value = "付款方式", index = 15)
    private String paymentMethod;

    @ExcelProperty(value = "折扣率（%）", index = 16)
    private Double discountRate;

    @ExcelProperty(value = "账期（天）", index = 17)
    private Integer accountPeriod;

    @ExcelProperty(value = "信用额度", index = 18)
    private Double lineCredit;

    @ExcelProperty(value = "纳税识别号", index = 19)
    private String payTaxesNum;

    @ExcelProperty(value = "开票类型", index = 20)
    private String billType;

    @ExcelProperty(value = "开户银行", index = 21)
    private Integer bankDeposit;

    @ExcelProperty(value = "开户帐号", index = 22)
    private String openAccount;

    @ExcelProperty(value = "业务员", index = 23)
    private String salesmanCode;

    @ExcelProperty(value = "状态", index = 24)
    private String isEnable;

    @ExcelProperty(value = "邮政编码", index = 25)
    private String zipCode;
}
