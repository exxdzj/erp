package com.exx.dzj.entity.newyeargoods;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName NewGiftBaseInfo
 * @Description:
 * @Author yangyun
 * @Date 2019/10/9 0009 15:23
 * @Version 1.0
 **/
@Data
public class NewGiftBaseInfo implements Serializable {

    private Integer id;
    private Integer detailId;
    private String categoryName;
    private String categoryCode;
    private Integer sortValue;
    private String stockCode; // 销售商品
    private String detailStockCode;
    private String showName; // 显示用
    private String stockName; // 销售商品全名
    private Double goodsNum; // 销售商品数量
    private BigDecimal sumVolume; // 销售商品金额

}
