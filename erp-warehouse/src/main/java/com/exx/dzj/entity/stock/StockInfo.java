package com.exx.dzj.entity.stock;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class StockInfo implements Serializable {
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
}