package com.exx.dzj.controller.user;

import com.exx.dzj.facade.user.UserFacade;
import com.exx.dzj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * 当用户登录成功后,进入主页通过 token 获取用户信息和用户角色
     * @return
     */
    @GetMapping("getUserRoles")
    public Result getUserInfo(HttpServletRequest request, @RequestParam(value="token") String token){
        Result result = Result.responseSuccess();
        result.setData(userFacade.getUserRoles(token));
        return result;
    }

    /**
     * 查询业务员列表数据
     * @return
     */
    @GetMapping("querySalesman")
    public Result querySalesman() {
        Result result = Result.responseSuccess();
        result.setData(userFacade.querySalesman());
        return result;
    }
}
