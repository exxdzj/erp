package com.exx.dzj.service.newyeargoods;

import com.exx.dzj.entity.newyeargoods.NewGiftBaseInfo;
import com.exx.dzj.entity.newyeargoods.NewGiftDeatilBean;
import com.exx.dzj.entity.newyeargoods.NewGiftMainBean;
import com.exx.dzj.entity.newyeargoods.NewYearGoodsBean;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-10-09-10:24
 */
public interface NewYearGoodsService {

    List<NewGiftBaseInfo> query2020NewYearList();

    NewYearGoodsBean queryDayData();
}
