package com.exx.dzj.service.newyeargoods.impl;

import com.exx.dzj.entity.newyeargoods.NewGiftBaseInfo;
import com.exx.dzj.entity.newyeargoods.NewGiftDeatilBean;
import com.exx.dzj.entity.newyeargoods.NewGiftMainBean;
import com.exx.dzj.entity.newyeargoods.NewYearGoodsBean;
import com.exx.dzj.mapper.newyeargoods.NewYearGoodsMapper;
import com.exx.dzj.service.newyeargoods.NewYearGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName NewYearGoodsServiceImpl
 * @Description:
 * @Author yangyun
 * @Date 2019/10/9 0009 10:25
 * @Version 1.0
 **/
@Service("newYearGoodsService")
public class NewYearGoodsServiceImpl implements NewYearGoodsService {
    @Autowired
    private NewYearGoodsMapper newYearGoodsMapper;

    @Override
    public List<NewGiftBaseInfo> query2020NewYearList() {
        return newYearGoodsMapper.query2020NewYearList();
    }

    @Override
    public NewYearGoodsBean queryDayData() {
        return newYearGoodsMapper.queryDayData();
    }
}
