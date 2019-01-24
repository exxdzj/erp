package com.exx.dzj.page;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangyun
 * @create 2019-01-15-16:32
 *
 */
@Data
public class BaseModule implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
}
