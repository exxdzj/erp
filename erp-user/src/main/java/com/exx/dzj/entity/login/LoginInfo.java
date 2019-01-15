package com.exx.dzj.entity.login;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author
 * @Date 2019/1/12 0012 13:47
 * @Description 登录参数
 */
@Data
@ToString
public class LoginInfo implements Serializable {

    private String username;

    private String password;

    private String usertoken;
}
