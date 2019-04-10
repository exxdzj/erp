package com.exx.dzj.service.sys;

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
}
