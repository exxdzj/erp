package com.exx.dzj.controller.sys;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.role.RoleBean;
import com.exx.dzj.entity.role.RoleMenuInfo;
import com.exx.dzj.entity.role.RoleQuery;
import com.exx.dzj.facade.sys.RoleFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.JsonUtils;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author
 * @Date 2019/4/2 0002 14:20
 * @Description 角色
 */
@RestController
@RequestMapping("sys/role/")
public class RoleController {

    @Autowired
    private RoleFacade roleFacade;

    /**
     * 获取角色列表数据
     * @param query
     * @return
     */
    @GetMapping("queryList")
    public Result queryList(String query) {
        RoleQuery queryParam = JsonUtils.jsonToPojo(query, RoleQuery.class);
        int pageNum = queryParam != null ? queryParam.getPage() : CommonConstant.DEFAULT_PAGE_NUM;
        int pageSize = queryParam != null ? queryParam.getLimit() : CommonConstant.DEFAULT_PAGE_SIZE;
        return roleFacade.queryList(pageNum, pageSize, queryParam);
    }

    /**
     * 获取角色列表数据(下拉框的数据)
     * @param request
     * @param response
     * @return
     */
    @GetMapping("list")
    public Result list(HttpServletRequest request, HttpServletResponse response){
        Result result = Result.responseSuccess();
        result.setData(roleFacade.queryList());
        return result;
    }

    /**
     * 根据用户编号 查询用户角色
     * @param userCode
     * @return
     */
    @GetMapping("queryRoleByUserCode")
    public Result queryRoleByUserCode(String userCode) {
        return roleFacade.queryRoleByUserCode(userCode);
    }

    /**
     * 保存 角色信息
     * @param bean
     * @return
     */
    @PostMapping("saveRole")
    public Result saveRole(@RequestBody RoleBean bean) {
        Result result = Result.responseSuccess();
        if(null == bean){
            result.setCode(400);
            result.setMsg("请填写角色数据!");
            return result;
        }
        if(StringUtils.isBlank(bean.getRoleCode())) {
            result.setCode(400);
            result.setMsg("请填写角色编码!");
            return result;
        }
        if(StringUtils.isBlank(bean.getRoleName())) {
            result.setCode(400);
            result.setMsg("请填写角色名称!");
            return result;
        }
        return roleFacade.saveRole(bean);
    }

    /**
     * 判断角色编码是否存在
     * @param roleCode
     * @return
     */
    @GetMapping("checkRole")
    public Result checkRole(String roleCode) {
        return roleFacade.checkRole(roleCode);
    }

    /**
     * 删除 角色
     * @param roleCode
     * @return
     */
    @PostMapping("cancelRole")
    public Result cancelRole(String roleCode) {
        Result result = Result.responseSuccess();
        if(StringUtils.isBlank(roleCode)){
            result.setCode(400);
            result.setMsg("请选择要删除的角色!");
            return result;
        }
        return roleFacade.cancelRole(roleCode);
    }

    /**
     * 查询角色所对应的菜单数据(权限数据)
     * @param roleCode
     * @return
     */
    @GetMapping("queryRolePermission")
    public Result queryRolePermission(String roleCode) {
        return roleFacade.queryRolePermission(roleCode);
    }

    /**
     * 角色授权
     * @param info
     * @return
     */
    @PostMapping("grantAuth")
    public Result grantAuth(@RequestBody RoleMenuInfo info){
        Result result = Result.responseSuccess();
        if(null == info){
            result.setCode(400);
            result.setMsg("请选择要授权的角色!");
            return result;
        }
        if(StringUtils.isBlank(info.getRoleCode())) {
            result.setCode(400);
            result.setMsg("请选择要授权的角色!");
            return result;
        }
        return roleFacade.grantAuth(info);
    }
}