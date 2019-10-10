package com.exx.dzj.entity.newyeargoods;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName NewGiftDeatilBean
 * @Description:
 * @Author yangyun
 * @Date 2019/10/9 0009 10:46
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class NewGiftDeatilBean extends NewGiftBaseBean {

    private String stockCode;
    private String stockName;
    private String showName;

    private List<NewYearGoodsBean> goodsList = new ArrayList<>();
}
