package com.exx.dzj.mapper.market;

import com.exx.dzj.entity.market.SaleInfo;
import com.exx.dzj.entity.market.SaleInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleInfoMapper {
    long countByExample(SaleInfoExample example);

    int deleteByExample(SaleInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SaleInfo record);

    int insertSelective(SaleInfo record);

    List<SaleInfo> selectByExample(SaleInfoExample example);

    SaleInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaleInfo record, @Param("example") SaleInfoExample example);

    int updateByExample(@Param("record") SaleInfo record, @Param("example") SaleInfoExample example);

    int updateByPrimaryKeySelective(SaleInfo record);

    int updateByPrimaryKey(SaleInfo record);
}