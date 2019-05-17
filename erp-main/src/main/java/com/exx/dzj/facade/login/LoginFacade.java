package com.exx.dzj.facade.login;

import com.exx.dzj.entity.login.LoginInfo;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.login.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author
 * @Date 2019/1/8 0008 14:43
 * @Description 登录、退出  中间层(主要是方便分布式开发时的项目拆分)
 */
@Component
public class LoginFacade {

    @Autowired
    private LoginService logonService;

    /**
     * 用户登录
     * @param loginInfo
     * @return 返回 userToken, 前端获取之后,将 userToken 放到 header 域中
     */
    public Result signIn(LoginInfo loginInfo){
        Result result = Result.responseSuccess();
        result = logonService.signIn(loginInfo);
        return result;
    }

    /**
     * 用户退出登录
     * @return
     */
    public Result loginOut(String userToken){
        Result result = Result.responseSuccess();
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsertoken(userToken);
        result = logonService.loginOut(loginInfo);
        return result;
    }
}
