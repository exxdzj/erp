package com.exx.dzj.service.sys;

import com.alibaba.fastjson.JSONArray;

/**
 * @Author
 * @Date 2019/3/22 0022 15:00
 * @Description 系统权限 (后期系统管理的模块多需要分离，单独一个工程)
 */
public interface SysPermissionService {

    /**
     * 根据用户角色获取权限
     * @param userCode
     * @return
     */
    JSONArray queryPermissionsByUser(String userCode);
}
