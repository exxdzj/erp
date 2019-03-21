package com.exx.dzj.service.user.impl;

import com.exx.dzj.mapper.user.UserRoleMapper;
import com.exx.dzj.service.user.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Date 2019/3/20 0020 10:54
 * @Description 用户角色
 */
@Service("userRolesService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 获取用户角色
     * @param userCode
     * @return
     */
    @Override
    public List<String> queryUserRoles(String userCode) {
        return userRoleMapper.queryUserRoles(userCode);
    }
}
