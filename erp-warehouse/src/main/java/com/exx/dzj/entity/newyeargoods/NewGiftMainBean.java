package com.exx.dzj.entity.newyeargoods;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName NewGiftMainBean
 * @Description:
 * @Author yangyun
 * @Date 2019/10/9 0009 10:43
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class NewGiftMainBean extends NewGiftBaseBean {

    private String categoryName;
    private Integer sortValue;

    private List<NewGiftDeatilBean> detailList = new ArrayList<>();
}
