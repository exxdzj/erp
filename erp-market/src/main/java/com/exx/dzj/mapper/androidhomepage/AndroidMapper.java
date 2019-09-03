package com.exx.dzj.mapper.androidhomepage;

import com.exx.dzj.entity.market.SaleInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author yangyun
 * @create 2019-08-26-18:13
 */
public interface AndroidMapper {

    SaleInfo queryPersonageSaleVolume(@Param("userCode") String userCode, @Param("type") String date);

    int queryCustomerCount(@Param("userCode") String userCode, @Param("newly") String newly);
}
