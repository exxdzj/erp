package com.exx.dzj.mapper.salelog;

import com.exx.dzj.entity.salelog.SaleLog;
import com.exx.dzj.entity.salelog.SaleLogExample;

import java.util.List;

public interface SaleLogMapper {

    int insertSelective(SaleLog record);

    List<SaleLog> selectByExample(SaleLogExample example);

    SaleLog selectByPrimaryKey(Integer id);
}