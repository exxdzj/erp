package com.exx.dzj.mapper.salelog;

import com.exx.dzj.entity.salelog.SaleLogBean;
import com.exx.dzj.entity.salelog.SaleLogExample;

import java.util.List;

public interface SaleLogMapper {

    int insertSelective(SaleLogBean record);

    List<SaleLogBean> selectByExample(SaleLogExample example);

    SaleLogBean selectByPrimaryKey(Integer id);
}