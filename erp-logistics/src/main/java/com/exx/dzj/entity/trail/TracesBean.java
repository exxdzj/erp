package com.exx.dzj.entity.trail;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author
 * @Date 2019/5/20 0020 10:34
 * @Description 物流的追踪信息
 */
@Data
public class TracesBean implements Serializable {

    /**
     * 物流信息(地址)
     */
    private String AcceptStation;

    /**
     * 时间(发货、签收)
     */
    private String AcceptTime;
}
