package com.exx.dzj.entity.user;

import lombok.Data;

import java.util.List;

/**
 * @Author
 * @Date 2019/4/3 0003 14:00
 * @Description 保存用户信息 model
 */
@Data
public class UserBean extends UserInfo{

    List<String> role;
}
