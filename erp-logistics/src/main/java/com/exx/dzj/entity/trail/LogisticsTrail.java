package com.exx.dzj.entity.trail;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Date 2019/5/18 0018 17:23
 * @Description 物流明细 model
 */
@Data
public class LogisticsTrail implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 货运单号(快递单号、物流单号)
     */
    private String freightCode;

    /**
     * 物流公司编码
     */
    private String logisticCompanyCode;

    /**
     * 业务单号(销售单号、采购单号)
     */
    private String orderNo;

    /**
     * 接收时间
     */
    private String acceptTime;

    /**
     * 接收站点
     */
    private String acceptStation;

    /**
     * 所属业务模块(1-销售单 、2-采购单)
     */
    private Integer busModule;

    /**
     * 数据写入时间
     */
    private Date createTime;

    private Integer dataSource;

    private String businessId;
}
