package com.exx.dzj.entity.market;

import com.exx.dzj.page.BaseModule;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class SaleInfo extends BaseModule {
    private static final long serialVersionUID = 4886114971525378166L;
    private Integer id;

    private String saleCode;

    private String userCode;

    private String salesmanCode;

    private String custCode;

    private String saleProject;

    private String saleProjectName;

    private Timestamp saleDate;

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

    /**
     * @description 收款人
     */
    private String collectionUserName;

    private Date updateTime;

    private String updateUser;

    /**销售单关联收款记录详情**/
    private List<SaleReceiptsDetails> saleReceiptsDetailsList = new ArrayList<>();

    /**销售关联单商品详情**/
    private List<SaleGoodsDetailBean> saleGoodsDetailBeanList = new ArrayList<>();

    /**客户名称**/
    private String custName;

    /**收款状态**/
    private  String paymentStatus;

    /**用户名*/
    private String userName;

    /*已收款总金额*/
    private BigDecimal sumCollectedAmount;

    /**收款方式**/
    private String paymentTerm;

    /*收款账户*/
    private String collectedAmounts;

    private Integer saleTicketType;

    private String freightCode;
    private String logisticsCompamyName;
    private String realName;

    /**销售单来源*/
    private String saleSource;
    private String saleSourceName;

    /**销售员所属公司*/
    private String subordinateCompanyCode;
    private String subordinateCompanyName;

    /**是否签收*/
    private Integer isReceipt;

    private Integer countTicket;
}