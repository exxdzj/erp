package com.exx.dzj.entity.purchase;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @description: 采购导出
 * @author yangyun
 * @date 2019/8/5 0005
 */
@Data
public class PurchaseListInfo implements Serializable {

    private static final long serialVersionUID = 367952495531005178L;
    /*****采购单信息*****/
    private Timestamp purchaseDate; // 采购时间
    private String purchaseCode; // 采购单号
    private String paymentStatus; // 状态
    private String custName; // 供应商
    private String custCode; // 供应商编码
    private String custPhoneNum; // 供应商电话
    private String currency; // 币别
    private Double exchangeRate; // 汇率
    private String deliveryAddress; // 送货地址
    private String salesmanName; // 采购员
    private String purchaseProject; // 项目
    private String purchaseOrderCode; // 采购订单号
    private String invoiceCode; // 发票编号
    private String purchaseRemark; // 采购单备注
    private BigDecimal purchaseSumVolume; // 采购总额
//    private Double
    private BigDecimal discountAmount; // 优惠
    private String collectionTerms; // 付款条件
    private Integer accountPeriod;// 账期
    private String createUser; // 付款人, 创建人
    private String flowStatus; // 审核状态
    private Timestamp dueDate; // 到期日
    /*****采购单信息*****/

    /****收款信息******/
    List<PurchaseReceiptListInfo> receiptList;
//    private BigDecimal collectedAmount; // 付款金额
//    private String collectionAccount; // 付款账户
//    private String paymentMethod; // 付款方式
    /****收款信息******/

    /****商品信息******/
    List<PurchaseGoodsListInfo> goodsList;
//    private String stockCode; // 存货编号
//    private String stockName; // 存货名称
//    private String stockAddress;// 存货地点
//    private Double goodsNum; // 数量
//    private BigDecimal realSellPrice; // 实际采购单价
//    private BigDecimal purchaseVolume; // 采购额
//    private String goodsRemark; // 商品备注
    /****商品信息******/
}
