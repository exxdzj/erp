package com.exx.dzj.entity.contactway;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户或供应商联系信息 实体类
 */
@Data
@ToString
public class ContactWayBean implements Serializable {
    private Integer id;

    /**
     * 客户或供应商code
     */
    private String custCode;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 电话号码
     */
    private String telephoneNum;

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 传真号码
     */
    private String faxNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 通讯地址
     */
    private String postalAddress;

    /**
     * 通讯地址(英文)
     */
    private String postalAddressEng;

    /**
     * 送货地址1
     */
    private String deliveryAddressOne;

    /**
     * 送货地址2
     */
    private String deliveryAddressTwo;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 是否可用  0-无 1-有
     */
    private Integer isEnable;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}