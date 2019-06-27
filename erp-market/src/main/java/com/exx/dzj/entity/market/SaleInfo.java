package com.exx.dzj.entity.market;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.exx.dzj.annotation.LIKE;
import com.exx.dzj.page.BaseModule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
@TableName("tab_sale_info")
@JsonIgnoreProperties(value = {"handler"})
public class SaleInfo extends BaseModule {
    private static final long serialVersionUID = 4886114971525378166L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String saleCode;

    private String userCode;

    private String salesmanCode;

    private String custCode;

    private String saleProject;

    private String saleProjectName;

    private Timestamp saleDate;

    private Integer saleTicketType;

    private String salesOrderCode;

    private String currency;

    private Double exchangeRate;

    private String deliveryOrderCode;

    private String custOrderCode;

    private String deliveryAddress;

    private String invoiceCode;

    private String billType;

    /**客户手机号码**/
    private String custPhoneNum;

    private BigDecimal totalSum;

    private BigDecimal discountAmount;

    private BigDecimal receivableAccoun;

    private BigDecimal receivedAmoun;

    private String receiptNum;

    private String collectionTerms;

    private Integer accountPeriod;

    private Date dueDate;

    /**是否签收*/
    private Integer isReceipt;

    /**销售员所属公司*/
    private String subordinateCompanyCode;

    private String subordinateCompanyName;

    /**销售单来源*/
    private String saleSource;

    private Integer isEnable;

    private String remarks;

    /**
     * @description 收款人
     */
    private String collectionUserName;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    /**收款状态**/
    private  String paymentStatus;

    /**收款方式**/
    private String paymentTerm;

    /**销售单关联收款记录详情**/
    @TableField(exist = false, select = false)
    private List<SaleReceiptsDetails> saleReceiptsDetailsList = new ArrayList<>();

    /**销售关联单商品详情**/
    @TableField(exist = false, select = false)
    private List<SaleGoodsDetailBean> saleGoodsDetailBeanList = new ArrayList<>();

    /**客户名称**/
    @TableField(exist = false, select = false)
    private String custName;

    /**用户名*/
    @TableField(exist = false, select = false)
    private String userName;

    /*已收款总金额*/
    @TableField(exist = false, select = false)
    private BigDecimal sumCollectedAmount;

    /*收款账户*/
    @TableField(exist = false, select = false)
    private String collectedAmounts;

    /*物流信息*/
    @TableField(exist = false, select = false)
    List<LogisticsInfo> logisticsInfoList;

//    private String freightCode;
//    private String logisticsCompamyName;

    @TableField(exist = false, select = false)
    private String realName;

    @TableField(exist = false, select = false)
    private String saleSourceName;

    @TableField(exist = false, select = false)
    private Integer countTicket;

    @TableField(exist = false, select = false)
    private String orgCode;

    @LIKE
    public String getSaleCode() {
        return saleCode;
    }
}