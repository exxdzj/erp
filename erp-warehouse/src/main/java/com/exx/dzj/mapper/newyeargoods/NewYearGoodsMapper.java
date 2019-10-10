package com.exx.dzj.mapper.newyeargoods;

import com.exx.dzj.entity.newyeargoods.NewGiftBaseInfo;
import com.exx.dzj.entity.newyeargoods.NewYearGoodsBean;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-10-09-10:26
 */
public interface NewYearGoodsMapper {

    List<NewGiftBaseInfo> query2020NewYearList();

    NewYearGoodsBean queryDayData();
}
