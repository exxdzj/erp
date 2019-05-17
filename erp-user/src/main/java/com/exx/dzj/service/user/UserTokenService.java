package com.exx.dzj.service.user;

import com.exx.dzj.entity.login.LoginInfo;
import com.exx.dzj.entity.user.UserTokenBean;

import java.util.Map;

/**
 * @Author
 * @Date 2019/1/8 0008 15:54
 * @Description user token service
 */
public interface UserTokenService {

    /**
     * 保存 user token
     * @param tokenBean
     */
    void saveUserToken(UserTokenBean tokenBean);

    /**
     * 根据 user token 获取 user code
     * @return
     */
    String queryUserCodeForToken(String userToken);

    /**
     * 生成 user token
     * @param userCode
     * @return
     */
    String getUserToken(String userCode);

    /**
     * 退出登录，删除 token
     * @param loginInfo
     */
    void loginOut(LoginInfo loginInfo);
}
