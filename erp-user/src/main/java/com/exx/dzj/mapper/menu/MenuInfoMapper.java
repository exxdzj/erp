package com.exx.dzj.mapper.menu;

import com.exx.dzj.entity.menu.MenuInfo;
import com.exx.dzj.entity.menu.MenuInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuInfoMapper {

    int insertSelective(MenuInfo record);

    List<MenuInfo> selectByExample(MenuInfoExample example);

    MenuInfo selectByPrimaryKey(@Param("menuCode") String menuCode);

    int updateByExampleSelective(@Param("record") MenuInfo record, @Param("example") MenuInfoExample example);

    int updateByPrimaryKeySelective(MenuInfo record);

    int queryOverlap(MenuInfo record);

    int queryMaxSeq();

    int cancelMenu(MenuInfo record);
}