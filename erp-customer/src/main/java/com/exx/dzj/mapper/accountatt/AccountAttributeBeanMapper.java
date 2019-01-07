package com.exx.dzj.mapper.accountatt;

import com.exx.dzj.entity.accountatt.AccountAttributeBean;
import com.exx.dzj.entity.accountatt.AccountAttributeBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountAttributeBeanMapper {
    long countByExample(AccountAttributeBeanExample example);

    int deleteByExample(AccountAttributeBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountAttributeBean record);

    int insertSelective(AccountAttributeBean record);

    List<AccountAttributeBean> selectByExample(AccountAttributeBeanExample example);

    AccountAttributeBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountAttributeBean record, @Param("example") AccountAttributeBeanExample example);

    int updateByExample(@Param("record") AccountAttributeBean record, @Param("example") AccountAttributeBeanExample example);

    int updateByPrimaryKeySelective(AccountAttributeBean record);

    int updateByPrimaryKey(AccountAttributeBean record);
}