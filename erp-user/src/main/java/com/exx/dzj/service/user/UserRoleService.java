package com.exx.dzj.service.user;

import java.util.List;

/**
 * @Author
 * @Date 2019/3/20 0020 10:52
 * @Description  用户角色
 */
public interface UserRoleService {

    /**
     * 获取用户角色
     * @param userCode
     * @return
     */
    List<String> queryUserRoles(String userCode);
}
