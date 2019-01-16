package com.exx.dzj.controller.user;

import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.facade.user.UserFacade;
import com.exx.dzj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result getUserInfo(){
        Result result = Result.responseSuccess();
        result.setData(userFacade.getUserInfo());
        return result;
    }

    /**
     * @description 获取业务员信息, 用作下拉
     * @author yangyun
     * @date 2019/1/15 0015
     * @param
     * @return com.exx.dzj.result.Result
     */
    @GetMapping("querysalesman")
    public Result querySalesman(){
        Result result = Result.responseSuccess();
        List<UserInfo> userInfos = userFacade.querySalesman();
        result.setData(userInfos);
        return result;
    }
}
