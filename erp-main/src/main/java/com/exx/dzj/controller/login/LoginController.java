package com.exx.dzj.controller.login;

import com.exx.dzj.entity.login.LoginInfo;
import com.exx.dzj.facade.login.LoginFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.shiro.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
                         @RequestBody LoginInfo loginInfo){
        Result result = Result.responseSuccess();
        if(null == loginInfo
                || !StringUtils.isNotBlank(loginInfo.getUsername())
                || !StringUtils.isNotBlank(loginInfo.getPassword())){
            result.setCode(400);
            result.setMsg("用户或密码不可为空!");
            return result;
        }
        //生成token
        String token = JwtUtil.sign(loginInfo.getUsername(), loginInfo.getPassword());
        loginInfo.setUsertoken(token);
        result = loginFacade.signIn(loginInfo);
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
