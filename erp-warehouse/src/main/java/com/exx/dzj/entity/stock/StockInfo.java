package com.exx.dzj.entity.stock;

import com.exx.dzj.page.BaseModule;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class StockInfo extends BaseModule {
    private Integer id;

    private String stockCode;

    private String nature;

    private String stockName;

    private String specificateType;

    private String stockClass;

    private String stockClassName;

    private String stockAddress;

    private String meterUnit;

    private String pictures;

    private String barCode;

    private Integer isShelves;

    private Integer isEnable;

    private Integer isCommSale;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    /**来源方式(0-存货， 1-采购，采购入库后需要修改为 0)*/
    private Integer sourceMode;

    /**版本号*/
    private Integer version;
}