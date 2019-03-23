package com.exx.dzj.mapper.sys;

import com.exx.dzj.entity.menu.MenuInfo;

import java.util.List;

/**
 * @Author
 * @Date 2019/3/22 0022 15:17
 * @Description 系统权限
 */
public interface SysPermissionMapper {

    List<MenuInfo> queryPermissionsByUser(String userCode);
}
