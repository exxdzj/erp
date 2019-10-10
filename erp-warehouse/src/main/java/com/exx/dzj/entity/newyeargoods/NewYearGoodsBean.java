package com.exx.dzj.entity.newyeargoods;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName NewYearGoodsBean
 * @Description:
 * @Author yangyun
 * @Date 2019/10/9 0009 15:49
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class NewYearGoodsBean implements Serializable {

    private String stockName;
    private String stockCode;
    private Double goodsNum;
    private BigDecimal sumVolume;
}
