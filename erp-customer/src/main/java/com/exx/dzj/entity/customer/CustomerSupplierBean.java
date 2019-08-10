package com.exx.dzj.entity.customer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户或供应商 实体类
 */
@Data
@ToString
@TableName("tab_customer_supplier")
public class CustomerSupplierBean implements Serializable {
    private String value;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    //客户编号
    private String custCode;

    //名称
    private String custName;

    //身份类型(1-客户  2-供应商  3-既是客户又是供应商 )
    private Integer custType;

    //客户身份(0-非保险  1-保险)
    private Integer identity;

    //客户职级(保险家等<类别>)
    private String custLevel;

    //职级名称(方便查询,减少表关联)
    private String levelName;

    //职级编码(在行业中的职级,例如:总监、经理)
    private String rankCode;

    //职级名称(方便查询,不用再去字典表关联查询)
    private String rankName;

    //业务员编码
    private String salesmanCode;

    //用户编码
    private String userCode;

    //地区编码
    private String regionCode;

    //地区名称
    private String regionName;

    //发货地址编码
    private String shipCode;

    //来源
    private Integer source;

    //发货地址
    private String shipAddress;

    // 生日
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Date birthday;

    //备注
    private String remarks;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    // 是否有效
    private Integer isEnable;

    private String phoneNum;

    private String companyCode;

    private String companyName;

    // 是否是问题客户 (0-非  1-是)
    private Integer isQuestion;

    // 问题原因
    private String questionDesc;

    /**
     * 客户等级编码
     *
     * pt0001 普通客户
     *
     * vip001 黄金客户2万或6次
     *
     * vip002 铂金客户4万或12次
     *
     * vip003 钻石客户10万或30次
     */
    private String gradeCode;

    // 客户等级名称
    private String custGrade;
}