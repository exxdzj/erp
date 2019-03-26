package com.exx.dzj.entity.customer;

import com.exx.dzj.entity.accountatt.AccountAttributeBean;
import com.exx.dzj.entity.contactway.ContactWayBean;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户或供应商 实体类
 */
@Data
@ToString
public class CustomerSupplierBean implements Serializable {
    private Integer id;

    //客户编号
    private String custCode;

    //名称
    private String custName;

    //身份类型(1-客户  2-供应商  3-既是客户又是供应商 )
    private int custType;

    //客户职级(保险家等<类别>)
    private String custLevel;

    //职级名称(方便查询,减少表关联)
    private String levelName;

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
    private int source;

    //发货地址
    private String shipAddress;

    //备注
    private String remarks;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    // 是否有效
    private Integer isEnable;
}