package com.exx.dzj.mapper.menu;

import com.exx.dzj.entity.menu.MenuInfo;
import com.exx.dzj.entity.menu.MenuInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuInfoMapper {
    long countByExample(MenuInfoExample example);

    int deleteByExample(MenuInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    List<MenuInfo> selectByExample(MenuInfoExample example);

    MenuInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MenuInfo record, @Param("example") MenuInfoExample example);

    int updateByExample(@Param("record") MenuInfo record, @Param("example") MenuInfoExample example);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);
}