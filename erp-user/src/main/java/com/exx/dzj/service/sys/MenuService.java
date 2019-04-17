package com.exx.dzj.service.sys;

import com.alibaba.fastjson.JSONArray;
import com.exx.dzj.entity.menu.MenuInfo;
import com.exx.dzj.entity.menu.MenuTreeBean;
import com.exx.dzj.result.Result;

import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Description 菜单 service
 */
public interface MenuService {

    /**
     * 查询 菜单树形结构数据
     * @return
     */
    Map<String, Object> queryMenuTree();

    /**
     * 查询 菜单树形结构
     * @return
     */
    List<MenuTreeBean> queryMenuList();

    /**
     * 查询 上级菜单下拉框数据
     * @return
     */
    JSONArray queryMenuData();

    /**
     * 保存 菜单信息
     * @param menuInfo
     * @return
     */
    Result saveMenu(MenuInfo menuInfo);

    /**
     * 查询 菜单详细信息
     * @param menuInfo
     * @return
     */
    MenuInfo queryMenuInfo(MenuInfo menuInfo);

    /**
     * 删除 菜单
     * @param menuInfo
     * @return
     */
    Result cancelMenu(MenuInfo menuInfo);
}
