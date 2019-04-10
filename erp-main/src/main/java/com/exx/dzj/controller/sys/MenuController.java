package com.exx.dzj.controller.sys;

import com.exx.dzj.facade.sys.MenuFacade;
import com.exx.dzj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 查询 菜单按钮
     * @return
     */
    @GetMapping("queryMenuTree")
    public Result queryMenuTree() {
        return menuFacade.queryMenuTree();
    }
}
