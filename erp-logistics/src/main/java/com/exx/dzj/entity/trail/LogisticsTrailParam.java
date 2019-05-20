package com.exx.dzj.entity.trail;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author
 * @Date 2019/5/18 0018 17:31
 * @Description 查询参数
 */
@Data
public class LogisticsTrailParam implements Serializable {

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
     * 所属业务模块(1-销售单 、2-采购单)
     */
    private Integer busModule;
}
