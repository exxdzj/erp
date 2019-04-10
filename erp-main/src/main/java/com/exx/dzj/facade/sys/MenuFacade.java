package com.exx.dzj.facade.sys;

import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
