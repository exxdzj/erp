package com.exx.dzj.entity.market;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangyun
 * @create 2019-06-04-16:38
 */
@Data
public class SaleGoodsTop implements Serializable {
    private static final long serialVersionUID = -1625820920068043223L;

    private String stockName;
    private String stockeCode;
    private Integer goodsNum;
}
