package com.exx.dzj.entity.newyeargoods;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName NewGiftBaseBean
 * @Description:
 * @Author yangyun
 * @Date 2019/10/9 0009 10:42
 * @Version 1.0
 **/
@Data
public class NewGiftBaseBean implements Serializable {
    private Integer id;
    private String categoryCode;
    private Integer isEnable;
    private BigDecimal sumVolume;
    private Double sumGoodsNum;
}
