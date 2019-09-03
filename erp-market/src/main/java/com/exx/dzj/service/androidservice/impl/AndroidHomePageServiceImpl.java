package com.exx.dzj.service.androidservice.impl;

import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.mapper.androidhomepage.AndroidMapper;
import com.exx.dzj.service.androidservice.AndroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SaleInfo queryPersonageSaleVolume(String userCode, String date) {
        return androidMapper.queryPersonageSaleVolume(userCode, date);
    }

    @Override
    public int queryCustomerCount(String userCode, String newly) {

        return androidMapper.queryCustomerCount(userCode, newly);
    }
}
