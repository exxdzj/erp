package com.exx.dzj.entity.stock;

import com.exx.dzj.page.BaseModule;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 存货实体类(商品类)
 */
@Data
@ToString
public class StockInfo extends BaseModule {
    /**主键*/
    private Integer id;
    /**存货code*/
    private String stockCode;
    /**存货性质(1-原材料 2-在产品 3-产成品  4-服务项目<服务项目性质的的存货不计入库存数量且不参与成本核算!>)*/
    private String nature;
    /**存货名称*/
    private String stockName;
    /**规格型号*/
    private String specificateType;
    /**存货类别*/
    private String stockClass;
    /**存货类别名称*/
    private String stockClassName;
    /**默认存货地点编码*/
    private String stockAddressCode;
    /**默认存货地点*/
    private String stockAddress;
    /**计量单位*/
    private String meterUnit;
    /**图片(可以多张)*/
    private String pictures;
    /**条形码*/
    private String barCode;
    /**是否上架(0-下架  1-上架)*/
    private Integer isShelves;
    /**是否可用(0-无 1-有)*/
    private Integer isEnable;
    /**是否受托代销商品(0-否  1-是)*/
    private Integer isCommSale;
    /**创建时间*/
    private Date createTime;
    /**创建人*/
    private String createUser;
    /**修改时间*/
    private Date updateTime;
    /**修改人*/
    private String updateUser;

    /**来源方式(0-存货， 1-采购，采购入库后需要修改为 0)*/
    private Integer sourceMode;

    /**版本号*/
    private Integer version;
}