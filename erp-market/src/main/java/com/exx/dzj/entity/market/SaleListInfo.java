package com.exx.dzj.entity.market;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-06-14-14:31
 */
@Data
@ToString
public class SaleListInfo implements Serializable {

    private static final long serialVersionUID = 4524804452219480914L;
    private String saleCode;

    private Timestamp saleDate;

    private String paymentStatus;

    private String custName;

    private String custCode;

    private String custPhoneNum;

    private String currency;

    private Double exchangeRate;

    private String salesmanName;

    private String saleProject;

    private String deliveryAddress;

    private String saleRemark;

    private Integer isReceipt;

    private String subordinateCompanyName;

    private BigDecimal discountAmount;

    private BigDecimal collectedAmount;

    private String collectionAccount;

    private String paymentMethod;

    private String collectionTerms;

    private Integer accountPeriod;

    private String collectionUserName;

    private Timestamp dueDate;

    private String stockName;
    private String deliverRemark;
    private String logistic;

    private String stockAddress;

    private Double goodsNum;

    private BigDecimal unitPrice;

    private BigDecimal discountRate;

    private BigDecimal goodsDiscountAmount;

    private BigDecimal salesVolume;

    private String goodsRemark;

    private BigDecimal sumCollectedAmount; // 已收款

    private List<LogisticsInfo> logisticsLsit; // 物流信息

    private BigDecimal receivableAccoun; // 收款额

    private String saleSource;

    /**销售单关联收款记录详情**/
    private List<SaleReceiptsDetails> saleReceiptsDetailsList = new ArrayList<>();

    /**销售关联单商品详情**/
    private List<SaleGoodsDetailBean> saleGoodsDetailBeanList = new ArrayList<>();
}
