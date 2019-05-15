package com.exx.dzj.entity.purchase;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 采购单实体类
 */
@Data
@ToString
public class PurchaseInfo implements Serializable {
    /**主键*/
    private Integer id;
    /**采购编号*/
    private String purchaseCode;
    /**采购员编码*/
    private String userCode;
    /**采购员编码(销售员和业务员对应)*/
    private String salesmanCode;
    /**供应商编号*/
    private String custCode;
    /**项目*/
    private String purchaseProject;
    /**日期*/
    private Date purchaseDate;
    /**采购订单号*/
    private String purchaseOrderCode;
    /**币别*/
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

    private List<PurchaseReceiptsDetailsBean> purchaseReceiptsDetailsBeans;

    private List<PurchaseGoodsDetailBean> purchaseGoodsDetailBeans;

    /**付款状态**/
    private String paymentStatus;

    /**供应商名称**/
    private String supplierName;

    /***采购员名称***/
    private String purchaseName;

    /**已收款总金额*/
    private BigDecimal sumCollectedAmount;

    /**收款账户*/
    private String collectedAmounts;

    /**客户名称**/
    private String custName;

    /**收款方式**/
    private String paymentTerm;

    /**采购流程状态(1-财务审批，2-仓库审批， 3-完成)*/
    private int flowState;
}