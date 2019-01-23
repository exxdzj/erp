package com.exx.dzj.entity.customer;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @Author
 * @Date 2019/1/22 0022 14:23
 * @Description 客户导入 model
 */
public class CustomerForExcelModel extends BaseRowModel {
    /**
     * 编号
     */
    @ExcelProperty(index = 0)
    private String custCode;

    /**
     * 属性
     */
    @ExcelProperty(index = 1)
    private String custType;

    /**
     * 名称
     */
    @ExcelProperty(index = 2)
    private String custName;

    /**
     * 类别
     */
    @ExcelProperty(index = 3)
    private String levelName;

    /**
     * 地区
     */
    @ExcelProperty(index = 4)
    private String regionName;

    /**
     * 备注
     */
    @ExcelProperty(index = 5)
    private String remarks;

    /**
     * 联系人
     */
    @ExcelProperty(index = 6)
    private String linkMan;

    /**
     * 电话号码
     */
    @ExcelProperty(index = 7)
    private String telephoneNum;

    /**
     * 手机号码
     */
    @ExcelProperty(index = 8)
    private String phoneNum;

    /**
     * 传真号码
     */
    @ExcelProperty(index = 9)
    private String faxNum;

    /**
     * E-Mail
     */
    @ExcelProperty(index = 10)
    private String email;

    /**
     * 通讯地址（本地）
     */
    @ExcelProperty(index = 11)
    private String postalAddress;

    /**
     * 通讯地址（英语）
     */
    @ExcelProperty(index = 12)
    private String postalAddressEng;

    /**
     * 送货地址（本地）
     */
    @ExcelProperty(index = 13)
    private String deliveryAddressOne;

    /**
     * 送货地址（英语）
     */
    @ExcelProperty(index = 14)
    private String deliveryAddressTwo;

    /**
     * 付款方式
     */
    @ExcelProperty(index = 15)
    private String paymentMethod;

    /**
     * 折扣率（%）
     */
    @ExcelProperty(index = 16)
    private Double discountRate;

    /**
     * 账期（天）
     */
    @ExcelProperty(index = 17)
    private Integer accountPeriod;

    /**
     * 信用额度
     */
    @ExcelProperty(index = 18)
    private Double lineCredit;

    /**
     * 纳税识别号
     */
    @ExcelProperty(index = 19)
    private String payTaxesNum;

    /**
     * 开票类型
     */
    @ExcelProperty(index = 20)
    private String billType;

    /**
     * 开户银行
     */
    @ExcelProperty(index = 21)
    private String bankDeposit;

    /**
     * 开户帐号
     */
    @ExcelProperty(index = 22)
    private String openAccount;

    /**
     * 业务员
     */
    @ExcelProperty(index = 23)
    private String salesmanCode;

    /**
     * 状态
     */
    @ExcelProperty(index = 24)
    private String status;

    /**
     * 邮政编码
     */
    @ExcelProperty(index = 25)
    private String zipCode;
}
