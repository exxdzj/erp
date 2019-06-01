package com.exx.dzj.entity.customer;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author 天刀
 * @Date 2019/1/5 0005 16:52
 * @Description 客户或供应商详细信息
 */
@Data
@ToString
public class CustomerSupplierInfo extends CustomerSupplierBean implements Serializable {

    /**
     * 供应商类型(供应商，或即是供应商又是客户)
     */
    private String custTypeName;
    private String prefix;

    /**
     * 联系信息
     */
    private String linkMan;
    private String telephoneNum;
    private String phoneNum;
    private String faxNum;
    private String email;
    private String postalAddress;
    private String postalAddressEng;
    private String deliveryAddressOne;
    private String deliveryAddressTwo;
    private String zipCode;

    /**
     * 会计属性
     */
    private String paymentMethod;
    private String paymentMethodName;
    private Double discountRate;
    private int accountPeriod;
    private Double lineCredit;
    private String payTaxesNum;
    private String billType;
    private String bankDeposit;
    private String openAccount;
}
