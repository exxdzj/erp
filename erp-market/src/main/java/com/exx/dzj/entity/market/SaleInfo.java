package com.exx.dzj.entity.market;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.exx.dzj.annotation.LIKE;
import com.exx.dzj.page.BaseModule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "销售单Model")
public class SaleInfo extends BaseModule {
    private static final long serialVersionUID = 4886114971525378166L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "销售单编码", name = "saleCode", required = true, example = "Z1907050001")
    private String saleCode;

    @ApiModelProperty(value = "用户编码", name = "userCode", required = true, example = "001")
    private String userCode;

    @ApiModelProperty(value = "业务员编码", name = "salesmanCode", required = true, example = "1001")
    private String salesmanCode;

    @ApiModelProperty(value = "客户编号", name = "custCode", required = true, example = "NF0798")
    private String custCode;

    @ApiModelProperty(value = "项目", name = "saleProject")
    private String saleProject;

    private String saleProjectName;

    @ApiModelProperty(value = "日期", name = "saleDate", required = true, example = "2019-07-01")
    private Timestamp saleDate;

    @ApiModelProperty(value = "单据类型(1:销售单,2:退款单)", name = "saleTicketType", example = "1")
    private Integer saleTicketType;

    private String salesOrderCode;

    private String currency;

    private Double exchangeRate;

    @ApiModelProperty(value = "送货订单号(快递单号)", name = "deliveryOrderCode", example = "201541521012141")
    private String deliveryOrderCode;

    private String custOrderCode;

    private String deliveryAddress;

    private String deliverRemark;

    private String invoiceCode;

    private String billType;

    /**客户手机号码**/
    @ApiModelProperty(value = "客户手机号码", name = "custPhoneNum", example = "13521111111")
    private String custPhoneNum;

    @ApiModelProperty(value = "总金额", name = "totalSum", required = true, example = "Z1907050001")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "优惠金额", name = "discountAmount", required = true, example = "Z1907050001")
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "应收款金额(总金额-优惠金额)", name = "receivableAccoun", required = true, example = "Z1907050001")
    private BigDecimal receivableAccoun;

    @ApiModelProperty(value = "实收金额(实际收款金额)", name = "receivedAmoun", required = true, example = "Z1907050001")
    private BigDecimal receivedAmoun;

    private String receiptNum;

    private String collectionTerms;

    @ApiModelProperty(value = "账期", name = "accountPeriod",  example = "12")
    private Integer accountPeriod;

    @ApiModelProperty(value = "到期日期", name = "saleCode", example = "2017-07-15")
    private Date dueDate;

    /**是否签收*/
    @ApiModelProperty(value = "是否签收(0:未签, 1:已签收, 2:其他)", name = "isReceipt", required = true, example = "0")
    private Integer isReceipt;

    /**销售员所属公司*/
    @ApiModelProperty(value = "销售员所属公司code", name = "subordinateCompanyCode", example = "201905081454131")
    private String subordinateCompanyCode;

    @ApiModelProperty(value = "所属公司名称", name = "subordinateCompanyName")
    private String subordinateCompanyName;

    /**销售单来源*/
    private String saleSource;

    private Integer isEnable;

    @ApiModelProperty(value = "备注", name = "remarks")
    private String remarks;

    /**
     * @description 收款人
     */
    @ApiModelProperty(value = "收款人(制单人)", name = "collectionUserName")
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

    @TableField(exist = false, select = false)
    private  String logistic;

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

    @TableField(exist = false, select = false)
    private String linkMan;

    @TableField(exist = false, select = false)
    private Integer goodsNum;

    @TableField(exist = false, select = false)
    private Date deliverGoodsTime;

    @LIKE
    public String getSaleCode() {
        return saleCode;
    }
}