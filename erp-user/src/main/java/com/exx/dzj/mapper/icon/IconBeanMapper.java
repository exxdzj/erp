package com.exx.dzj.mapper.icon;

import com.exx.dzj.entity.icon.IconBean;
import com.exx.dzj.entity.icon.IconBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IconBeanMapper {
    long countByExample(IconBeanExample example);

    int deleteByExample(IconBeanExample example);

    int deleteByPrimaryKey(String id);

    int insert(IconBean record);

    int insertSelective(IconBean record);

    List<IconBean> selectByExample(IconBeanExample example);

    IconBean selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") IconBean record, @Param("example") IconBeanExample example);

    int updateByExample(@Param("record") IconBean record, @Param("example") IconBeanExample example);

    int updateByPrimaryKeySelective(IconBean record);

    int updateByPrimaryKey(IconBean record);
}