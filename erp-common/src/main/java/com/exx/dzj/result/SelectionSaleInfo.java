package com.exx.dzj.result;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyun
 * @create 2019-04-19-11:32
 */
@Data
public class SelectionSaleInfo implements Serializable {
    private static final long serialVersionUID = 218924013920394847L;

    private String code;
    private String label;
    private List<SelectionSaleInfo> children = new ArrayList<>();
}
