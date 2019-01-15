package com.exx.dzj.entity.stock;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author
 * @Date 2019/1/12 0012 17:40
 * @Description 存货
 */
@Data
@ToString
public class StockQuery implements Serializable {
    private int page;

    private int limit;
}
