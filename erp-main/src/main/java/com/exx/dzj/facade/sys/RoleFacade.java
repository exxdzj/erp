package com.exx.dzj.facade.sys;

import com.exx.dzj.entity.role.RoleBean;
import com.exx.dzj.entity.role.RoleMenuInfo;
import com.exx.dzj.entity.role.RoleQuery;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.facade.user.UserTokenFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author
 * @Date 2019/4/2 0002 14:44
 * @Description 角色
 */
@Component
public class RoleFacade {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserTokenFacade userTokenFacade;

    /**
     * 查询 角色列表数据
     * @param pageNum
     * @param pageSize
     * @param query
     * @return
     */
    public Result queryList(int pageNum, int pageSize, RoleQuery query) {
        return roleService.queryList(pageNum, pageSize, query);
    }

    /**
     * 查询 角色列表数据(下拉框数据)
     * @return
     */
    public List<RoleBean> queryList() {
        return roleService.queryList();
    }

    /**
     * 根据用户编码查询用户角色
     * @param userCode
     * @return
     */
    public Result queryRoleByUserCode(String userCode) {
        Result result = Result.responseSuccess();
        result.setData(roleService.queryRoleByUserCode(userCode));
        return result;
    }

    /**
     * 保存 用户角色
     * @param userCode
     * @param roles
     * @return
     */
    public Result saveUserRole(String userCode, List<String> roles) throws ErpException{
        String operator = userTokenFacade.queryUserCodeForToken(null);
        return roleService.saveUserRole(userCode, operator, roles);
    }

    /**
     * 删除 用户角色
     * @param userCode
     * @return
     */
    public Result delByUserCode(String userCode) throws ErpException {
        return roleService.delByUserCode(userCode);
    }

    /**
     * 保存 角色数据
     * @param bean
     * @return
     */
    public Result saveRole(RoleBean bean) {
        String operator = userTokenFacade.queryUserCodeForToken(null);
        bean.setCreateUser(operator);
        bean.setUpdateUser(operator);
        return roleService.saveRole(bean);
    }

    /**
     * 检查角色编码是否存在
     * @param roleCode
     * @return
     */
    public Result checkRole(String roleCode) {
        return roleService.checkRole(roleCode);
    }

    /**
     * 根据角色编码查询角色所对应的菜单
     * @param roleCode
     * @return
     */
    public Result queryRolePermission(String roleCode) {
        Result result = Result.responseSuccess();
        result.setData(roleService.queryRolePermission(roleCode));
        return result;
    }

    /**
     * 删除 角色
     * @param roleCode
     * @return
     */
    public Result cancelRole(String roleCode) {
        String operator = userTokenFacade.queryUserCodeForToken(null);
        return roleService.cancelRole(roleCode, operator);
    }

    /**
     * 角色授权
     * @return
     */
    public Result grantAuth(RoleMenuInfo info) {
        Result result = Result.responseSuccess();
        String operator = userTokenFacade.queryUserCodeForToken(null);
        try {
            roleService.grantAuth(info, operator);
        } catch(ErpException e) {
            result.setCode(400);
            result.setMsg("角色授权失败!");
        }
        return result;
    }
}
