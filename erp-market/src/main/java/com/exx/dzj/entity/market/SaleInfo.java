package com.exx.dzj.entity.market;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class SaleInfo implements Serializable {
    private Integer id;

    private String saleCode;

    private String userCode;

    private String salesmanCode;

    private String custCode;

    private String saleProject;

    private String saleProjectName;

    private Date saleDate;

    private String salesOrderCode;

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

    /**销售单关联收款记录详情**/
    private List<SaleReceiptsDetails> saleReceiptsDetailsList;

    /**销售关联单商品详情**/
    private List<SaleGoodsDetailBean> saleGoodsDetailBeanList;

    /**客户名称**/
    private String custName;

    /**收款状态**/
    private  String paymentStatus;

    /**用户名*/
    private String userName;

    /*已收款总金额*/
    private BigDecimal sumCollectedAmount;
}