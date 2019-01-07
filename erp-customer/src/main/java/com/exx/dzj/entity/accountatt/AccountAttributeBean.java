package com.exx.dzj.entity.accountatt;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户或供应商会计信息  实体类
 */
@Data
@ToString
public class AccountAttributeBean implements Serializable {
    private Integer id;

    /**
     * 客户或供应商code
     */
    private String custCode;

    /**
     * 付款方式
     */
    private String paymentMethod;

    /**
     * 折扣率
     */
    private Double discountRate;

    /**
     * 账期(天)
     */
    private Integer accountPeriod;

    /**
     * 信用额度
     */
    private Double lineCredit;

    /**
     * 纳税识别号
     */
    private String payTaxesNum;

    /**
     * 开票类型
     */
    private String billType;

    /**
     * 开户银行
     */
    private String bankDeposit;

    /**
     * 开户帐号
     */
    private String openAccount;

    private Integer isEnable;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}