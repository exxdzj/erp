package com.exx.dzj.entity.purchase;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class PurchaseInfo implements Serializable {
    private Integer id;

    private String purchaseCode;

    private String userCode;

    private String salesmanCode;

    private String custCode;

    private String purchaseProject;

    private Date purchaseDate;

    private String purchaseOrderCode;

    private String currency;

    private Double exchangeRate;

    private String deliveryOrderCode;

    private String custOrderCode;

    private String deliveryAddress;

    private String invoiceCode;

    private String billType;

    private BigDecimal totalSum;

    private BigDecimal discountAmount;

    private BigDecimal receivableAccoun;

    private BigDecimal receivedAmoun;

    private String receiptNum;

    private String collectionTerms;

    private Integer accountPeriod;

    private Date dueDate;

    private Integer isEnable;

    private String remarks;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}