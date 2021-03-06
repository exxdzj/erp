package com.exx.dzj.entity.customer;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author 天刀
 * @Date 2019/1/5 0005 16:32
 * @Description 客户与供应商的列表数据 model
 */
@Data
@ToString
public class CustomerSupplierModel implements Serializable {
    private String value;
    /** 编号 */
    private String custCode;
    private String userCode;

    /** 供应商或客户的名称 */
    private String custName;

    /** 联系人 */
    private String linkMan;

    /** 邮箱 */
    private String email;

    /** 业务员 */
    private String salesman;

    /** 类别名称 */
    private String levelName;

    /** 折扣率 */
    private Double discountRate;

    /** 账期 */
    private Integer accountPeriod;

    /** 地区 */
    private String regionName;

    /** 数据状态(使用中、关闭) */
    private String dataStatus;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 职级
     */
    private String rankName;

    private String phoneNum;
    private String deliveryAddressOne;

    private String companyName;
}
