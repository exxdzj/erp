package com.exx.dzj.controller.login;

import com.exx.dzj.facade.login.LoginFacade;
import com.exx.dzj.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author
 * @Date 2019/1/8 0008 13:50
 * @Description 登录、退出  等操作
 */
@RestController
@RequestMapping("login/")
public class LoginController {

    @Autowired
    private LoginFacade loginFacade;

    /**
     * 登录
     * @param request
     * @param response
     * @return
     */
    @PostMapping("signIn")
    public Result signIn(HttpServletRequest request, HttpServletResponse response,
                         String userName, String password){
        Result result = Result.responseSuccess();
        if(!StringUtils.isNotBlank(userName) || !StringUtils.isNotBlank(password)){
            result.setCode(400);
            result.setMsg("用户或密码不可为空!");
            return result;
        }
        result = loginFacade.signIn(userName, password);
        return result;
    }

    /**
     * 退出-登录
     * @param request
     * @param response
     * @return
     */
    @PostMapping("loginOut")
    public Result loginOut(HttpServletRequest request, HttpServletResponse response){
        Result result = Result.responseSuccess();

        return result;
    }
}
