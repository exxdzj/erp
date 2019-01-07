package com.exx.dzj.mapper.market;

import com.exx.dzj.entity.market.SaleReceiptsDetails;
import com.exx.dzj.entity.market.SaleReceiptsDetailsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleReceiptsDetailsMapper {
    long countByExample(SaleReceiptsDetailsExample example);

    int deleteByExample(SaleReceiptsDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SaleReceiptsDetails record);

    int insertSelective(SaleReceiptsDetails record);

    List<SaleReceiptsDetails> selectByExample(SaleReceiptsDetailsExample example);

    SaleReceiptsDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaleReceiptsDetails record, @Param("example") SaleReceiptsDetailsExample example);

    int updateByExample(@Param("record") SaleReceiptsDetails record, @Param("example") SaleReceiptsDetailsExample example);

    int updateByPrimaryKeySelective(SaleReceiptsDetails record);

    int updateByPrimaryKey(SaleReceiptsDetails record);
}