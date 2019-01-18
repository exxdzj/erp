package com.exx.dzj.entity.stock;

import com.exx.dzj.page.BaseModule;
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
public class StockQuery extends StockInfo {
    private int page;

    private int limit;
}
