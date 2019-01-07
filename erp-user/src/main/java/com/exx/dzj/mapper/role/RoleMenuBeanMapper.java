package com.exx.dzj.mapper.role;

import com.exx.dzj.entity.role.RoleMenuBean;
import com.exx.dzj.entity.role.RoleMenuBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuBeanMapper {
    long countByExample(RoleMenuBeanExample example);

    int deleteByExample(RoleMenuBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenuBean record);

    int insertSelective(RoleMenuBean record);

    List<RoleMenuBean> selectByExample(RoleMenuBeanExample example);

    RoleMenuBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleMenuBean record, @Param("example") RoleMenuBeanExample example);

    int updateByExample(@Param("record") RoleMenuBean record, @Param("example") RoleMenuBeanExample example);

    int updateByPrimaryKeySelective(RoleMenuBean record);

    int updateByPrimaryKey(RoleMenuBean record);
}