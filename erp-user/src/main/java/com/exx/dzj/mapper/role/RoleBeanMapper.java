package com.exx.dzj.mapper.role;

import com.exx.dzj.entity.role.RoleBean;
import com.exx.dzj.entity.role.RoleBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleBeanMapper {
    long countByExample(RoleBeanExample example);

    int deleteByExample(RoleBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleBean record);

    int insertSelective(RoleBean record);

    List<RoleBean> selectByExample(RoleBeanExample example);

    RoleBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleBean record, @Param("example") RoleBeanExample example);

    int updateByExample(@Param("record") RoleBean record, @Param("example") RoleBeanExample example);

    int updateByPrimaryKeySelective(RoleBean record);

    int updateByPrimaryKey(RoleBean record);
}