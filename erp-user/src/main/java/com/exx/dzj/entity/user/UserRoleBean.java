package com.exx.dzj.entity.user;

import lombok.Data;

import java.util.List;

/**
 * @Author
 * @Date 2019/5/16 0016 11:26
 * @Description 用户角色(一个用户可以有多个角色)
 */
@Data
public class UserRoleBean extends UserRole{

    private List<String> roles;
}
