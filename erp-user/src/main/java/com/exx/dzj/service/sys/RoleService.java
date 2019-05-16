package com.exx.dzj.service.sys;

import com.exx.dzj.entity.role.RoleBean;
import com.exx.dzj.entity.role.RoleMenuInfo;
import com.exx.dzj.entity.role.RoleQuery;
import com.exx.dzj.entity.user.UserRole;
import com.exx.dzj.entity.user.UserRoleBean;
import com.exx.dzj.result.Result;

import java.util.List;

/**
 * @Author
 * @Date 2019/4/2 0002 14:29
 * @Description
 */
public interface RoleService {

    /**
     * 查询角色列表数据
     * @param pagaNum
     * @param pageSize
     * @param query
     * @return
     */
    Result queryList(int pagaNum, int pageSize, RoleQuery query);

    /**
     * 查询 角色列表数据(下拉框数据)
     * @return
     */
    List<RoleBean> queryList();

    /**
     * 根据用户编码查询用户角色
     * @param userCode
     * @return
     */
    List<UserRole> queryRoleByUserCode(String userCode);

    /**
     * 保存 用户角色
     * @param urBean
     * @return
     */
    Result saveUserRole(UserRoleBean urBean);

    /**
     * 删除 用户角色
     * @param userCode
     * @return
     */
    Result delByUserCode(String userCode);

    /**
     * 保存 角色数据
     * @param info
     * @return
     */
    Result saveRole(RoleBean info);

    /**
     * 检查角色编码是否存在
     * @param roleCode
     * @return
     */
    Result checkRole(String roleCode);

    /**
     * 查询 角色所对应的菜单数据
     * @param roleCode
     * @return
     */
    List<String> queryRolePermission(String roleCode);

    /**
     * 删除角色
     * @param roleCode
     * @param operator
     * @return
     */
    Result cancelRole(String roleCode, String operator);

   /**
     * 角色 授权
     * @param info
     * @return
     */
   void grantAuth(RoleMenuInfo info, String operator);
}
