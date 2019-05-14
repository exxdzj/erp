package com.exx.dzj.controller.login;

import com.exx.dzj.entity.login.LoginInfo;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.facade.login.LoginFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.shiro.util.JwtUtil;
import com.exx.dzj.shiro.vo.DefContants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author
 * @Date 2019/1/8 0008 13:50
 * @Description 登录、退出  等操作
 */
@Slf4j
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
        //首先 Subject 登出
        Subject subject = SecurityUtils.getSubject();
        //UserInfo userInfo = (UserInfo)subject.getPrincipal();
        //log.info("用户{}退出登录,时间{}", userInfo.getRealName(), new Date());
        subject.logout();

        // 如果有缓存，则需要将缓存中的数据清除(如果是在分布式环境下)

        // 删除数据库中的 token 数据(非分布式环境下，但是具体的还需要根据具体的需求来实现)
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        result = loginFacade.loginOut(token);
        return result;
    }
}
