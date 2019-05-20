package com.exx.dzj.entity.trail;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author
 * @Date 2019/5/20 0020 10:30
 * @Description  第三方接口获取到的物流信息解析后的对象
 */
@Data
public class TrailJsonBean implements Serializable {

    /**
     * 物流(快递)单号
     */
    private String LogisticCode;
    /**
     * 物流公司编码(要与第三方的对应)
     */
    private String ShipperCode;

    /**
     * 追踪的信息
     */
    List<TracesBean> Traces;

    /**
     * 物流状态(2-在途中,3-签收,4-问题件)
     */
    private Integer State;

    /**
     * 商户ID
     */
    private String EBusinessID;

    private boolean Success;
}
