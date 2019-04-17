package com.exx.dzj.controller.sys;

import com.exx.dzj.entity.menu.MenuInfo;
import com.exx.dzj.facade.sys.MenuFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author
 * @Date 2019/4/9 0009 11:01
 * @Description 菜单 controller
 */
@RestController
@RequestMapping("sys/menu/")
public class MenuController {

    @Autowired
    private MenuFacade menuFacade;

    /**
     * 查询 菜单树形结构数据(角色权限分配功能)
     * @return
     */
    @GetMapping("queryMenuTree")
    public Result queryMenuTree() {
        return menuFacade.queryMenuTree();
    }

    /**
     * 查询 菜单的树形结构列表数据(菜单管理模块)
     * @return
     */
    @GetMapping("queryMenuList")
    public Result queryMenuList() {
        return menuFacade.queryMenuList();
    }

    /**
     * 查询 上级菜单下拉框数据
     * @return
     */
    @GetMapping("queryMenuData")
    public Result queryMenuData() {
        return menuFacade.queryMenuData();
    }

    /**
     * 保存 菜单数据信息
     * @param menuInfo
     * @return
     */
    @PostMapping("saveMenu")
    public Result saveMenu(@RequestBody MenuInfo menuInfo) {
        Result result = Result.responseSuccess();
        if(null == menuInfo) {
            result.setCode(400);
            result.setMsg("数据不可为空,请填写数据!");
            return result;
        }
        if(ConvertUtils.isEmpty(menuInfo.getMenuName())) {
            result.setCode(400);
            result.setMsg("请填写名称!");
            return result;
        }
        return menuFacade.saveMenu(menuInfo);
    }

    /**
     * 查询 菜单的详细信息
     * @param menuInfo
     * @return
     */
    @GetMapping("queryMenuInfo")
    public Result queryMenuInfo(MenuInfo menuInfo) {
        return menuFacade.queryMenuInfo(menuInfo);
    }

    /**
     * 删除  菜单(非物理删除)
     * @param menuInfo
     * @return
     */
    @PostMapping("cancelMenu")
    public Result cancelMenu(@RequestBody MenuInfo menuInfo) {
        return menuFacade.cancelMenu(menuInfo);
    }
}
