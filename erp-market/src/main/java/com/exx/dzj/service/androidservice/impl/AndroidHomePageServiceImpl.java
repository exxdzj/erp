package com.exx.dzj.service.androidservice.impl;

import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.mapper.androidhomepage.AndroidMapper;
import com.exx.dzj.service.androidservice.AndroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AndroidHomePageServiceImpl
 * @Description:
 * @Author yangyun
 * @Date 2019/8/26 0026 18:11
 * @Version 1.0
 **/
@Service("androidService")
public class AndroidHomePageServiceImpl implements AndroidService {

    @Autowired
    private AndroidMapper androidMapper;

    @Override
    public List<SaleInfo> queryPersonageSaleVolume(List<String> list, String date) {
        return androidMapper.queryPersonageSaleVolume(list, date);
    }

    @Override
    public int queryCustomerCount(List<String> list, String newly) {

        return androidMapper.queryCustomerCount(list, newly);
    }

    @Override
    public List<Integer> queryPersonageNum(List<String> list, String date) {
        return androidMapper.queryPersonageNum(list, date);
    }
}
