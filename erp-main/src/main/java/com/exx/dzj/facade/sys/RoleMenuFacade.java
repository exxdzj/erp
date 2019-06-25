package com.exx.dzj.facade.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exx.dzj.entity.role.RoleMenuBean;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Date 2019/6/3 0003 16:14
 * @Description 角色和权限关联
 */
@Component
public class RoleMenuFacade {

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 查询 角色对应的权限数据
     * @param menuCode
     * @param roleCode
     * @return
     */
    public RoleMenuBean queryRoleMenu(String menuCode, String roleCode) {
        LambdaQueryWrapper<RoleMenuBean> query = new LambdaQueryWrapper<>();
        query.eq(RoleMenuBean::getMenuCode, menuCode);
        query.eq(RoleMenuBean::getRoleCode, roleCode);
        return roleMenuService.getOne(query);
    }

    /**
     * 给角色配置数据权限
     * @param info
     * @return
     */
    public Result datarule(RoleMenuBean info) {
        Result result = Result.responseSuccess();
        LambdaQueryWrapper<RoleMenuBean> query = new LambdaQueryWrapper<>();
        query.eq(RoleMenuBean::getMenuCode, info.getMenuCode());
        query.eq(RoleMenuBean::getRoleCode, info.getRoleCode());
        RoleMenuBean bean = roleMenuService.getOne(query);
        if(null == bean) {
            // 保存
            result.setCode(400);
            result.setMsg("请先保存角色菜单权限!");
            return result;
        } else {
            // 修改
            RoleMenuBean roleMenuBean = new RoleMenuBean();
            roleMenuBean.setDataRuleIds(info.getDataRuleIds());
            roleMenuService.update(roleMenuBean, query);
        }
        return result;
    }
}
