package com.exx.dzj.mapper.stock;

import com.exx.dzj.entity.stock.StockInfo;
import com.exx.dzj.entity.stock.StockInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockInfoMapper {
    long countByExample(StockInfoExample example);

    int deleteByExample(StockInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StockInfo record);

    int insertSelective(StockInfo record);

    List<StockInfo> selectByExample(StockInfoExample example);

    StockInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StockInfo record, @Param("example") StockInfoExample example);

    int updateByExample(@Param("record") StockInfo record, @Param("example") StockInfoExample example);

    int updateByPrimaryKeySelective(StockInfo record);

    int updateByPrimaryKey(StockInfo record);
}