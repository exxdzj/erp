package com.exx.dzj.service.androidservice;

import com.exx.dzj.entity.market.SaleInfo;

import java.util.List;

/**
 * @ClassName AndroidHomePageService
 * @Description:
 * @Author yangyun
 * @Date 2019/8/26 0026 18:10
 * @Version 1.0
 **/
public interface AndroidService {

    SaleInfo queryPersonageSaleVolume(String userCode, String date);

    int queryCustomerCount (String userCode, String newly);
}
