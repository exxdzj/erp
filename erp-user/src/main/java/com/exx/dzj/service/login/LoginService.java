package com.exx.dzj.service.login;

import com.exx.dzj.entity.login.LoginInfo;
import com.exx.dzj.result.Result;

/**
 * @Author
 * @Date 2019/1/8 0008 15:31
 * @Description 登录 service
 */
public interface LoginService {

    /**
     * 用户登录
     * @param loginInfo
     * @return 返回 userToken, 前端获取之后,将 userToken 放到 header 域中
     */
    Result signIn(String type, LoginInfo loginInfo);

    /**
     * 用户退出登录
     * @return
     */
    Result loginOut(LoginInfo loginInfo);
}
