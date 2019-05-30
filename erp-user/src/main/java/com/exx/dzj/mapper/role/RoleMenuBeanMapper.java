package com.exx.dzj.mapper.role;

import com.exx.dzj.entity.role.RoleMenuBean;
import com.exx.dzj.entity.role.RoleMenuBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuBeanMapper {

    int deleteByExample(RoleMenuBeanExample example);

    int insertSelective(RoleMenuBean record);

    List<RoleMenuBean> selectByExample(RoleMenuBeanExample example);

    RoleMenuBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleMenuBean record, @Param("example") RoleMenuBeanExample example);

    int updateByPrimaryKeySelective(RoleMenuBean record);

    List<String> queryRolePermission(@Param("roleCode") String roleCode);

    int grantAuth(List<RoleMenuBean> list);
}