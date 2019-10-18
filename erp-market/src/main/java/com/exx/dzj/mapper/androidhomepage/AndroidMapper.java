package com.exx.dzj.mapper.androidhomepage;

import com.exx.dzj.entity.market.SaleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yangyun
 * @create 2019-08-26-18:13
 */
public interface AndroidMapper {

    List<SaleInfo> queryPersonageSaleVolume(@Param("listParam") List<String> listParam, @Param("type") String date);

    int queryCustomerCount(@Param("listParam") List<String> listParam, @Param("newly") String newly);

    List<Integer> queryPersonageNum(@Param("listParam") List<String> list, @Param("type") String date);
}
