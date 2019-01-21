package com.exx.dzj.controller.user;

import com.exx.dzj.facade.user.UserFacade;
import com.exx.dzj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author
 * @Date 2019/1/8 0008 17:18
 * @Description 获取用户信息
 */
@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    /**
     * 当用户登录成功后,进入主页获取用户信息
     * @return
     */
    @GetMapping("getUserInfo")
    public Result getUserInfo(HttpServletRequest request){
        Result result = Result.responseSuccess();
        HttpSession session = request.getSession(true);
        //分布式环境下不要将 token 放在 session 对象中, 最好是放在分布式缓存中间件,例如 redis
        String token = (String)session.getAttribute("userToken");
        result.setData(userFacade.getUserInfo(token));
        return result;
    }

    @GetMapping("querySalesman")
    public Result querySalesman() {
        Result result = Result.responseSuccess();
        result.setData(userFacade.querySalesman());
        return result;
    }
}
