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

    List<SaleInfo> queryPersonageSaleVolume(List<String> list, String date);

    List<SaleInfo> queryPersonageSaleVolumeByDay(List<String> list, String date, Integer dayCount);

    int queryCustomerCount (List<String> list, String newly);

    List<Integer> queryPersonageNum(List<String> list, String date);
}
