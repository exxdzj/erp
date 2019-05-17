package com.exx.dzj.entity.market;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangyun
 * @create 2019-05-17-15:26
 */
@Data
public class SaleGoodsSelected implements Serializable {
    private static final long serialVersionUID = 5192916086859129704L;

    private String stockCode;
    private String stockName;
    private String saleCode;

}
