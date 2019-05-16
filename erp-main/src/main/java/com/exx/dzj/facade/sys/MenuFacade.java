package com.exx.dzj.facade.sys;

import com.exx.dzj.entity.menu.MenuInfo;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author
 * @Date 2019/4/9 0009 11:05
 * @Description  菜单
 */
@Component
public class MenuFacade {

    @Autowired
    private MenuService menuService;

    /**
     * 查询 菜单的树形结构
     * @return
     */
    public Result queryMenuTree() {
        Result result = Result.responseSuccess();
        result.setData(menuService.queryMenuTree());
        return result;
    }

    /**
     * 获取 菜单的树形结构列表数据
     * @return
     */
    public Result queryMenuList() {
        Result result = Result.responseSuccess();
        result.setData(menuService.queryMenuList());
        return result;
    }

    /**
     * 查询 上级菜单下拉框数据
     * @return
     */
    public Result queryMenuData() {
        Result result = Result.responseSuccess();
        result.setData(menuService.queryMenuData());
        return result;
    }

    /**
     * 保存 菜单信息
     * @param menuInfo
     * @return
     */
    public Result saveMenu(MenuInfo menuInfo) {
        Result result = Result.responseSuccess();
        try {
            result = menuService.saveMenu(menuInfo);
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("保存菜单数据失败!");
        }
        return result;
    }

    /**
     * 查询 菜单详细信息
     * @param menuInfo
     * @return
     */
    public Result queryMenuInfo(MenuInfo menuInfo) {
        Result result = Result.responseSuccess();
        result.setData(menuService.queryMenuInfo(menuInfo));
        return result;
    }

    /**
     * 删除 菜单
     * @param menuInfo
     * @return
     */
    public Result cancelMenu(MenuInfo menuInfo) {
        Result result = Result.responseSuccess();
        try {
            result = menuService.cancelMenu(menuInfo);
        } catch(ErpException ex) {
            result.setCode(400);
            result.setMsg("删除菜单失败!");
        }
        return result;
    }
}
