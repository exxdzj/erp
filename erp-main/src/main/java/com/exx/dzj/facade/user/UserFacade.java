package com.exx.dzj.facade.user;

import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author
 * @Date 2019/1/8 0008 17:04
 * @Description 获取用户信息
 */
@Component
public class UserFacade {

    @Autowired
    private UserTokenFacade userTokenFacade;

    @Autowired
    private UserService salesmanService;

    /**
     * 获取 用户信息
     * @return
     */
    public UserInfo getUserInfo(String userToken){
        String userCode = userTokenFacade.queryUserCodeForToken(userToken);
        return salesmanService.queryUserInfo(userCode);
    }

    /**
     * 保存业务员信息
     * @param userInfo
     * @return
     */
    public Result saveUserInfo(UserInfo userInfo){
        Result result = Result.responseSuccess();
        try{
            salesmanService.saveSalesman(userInfo);
        }catch(Exception ex){
            result.setCode(400);
            result.setMsg("保存业务员信息失败!");
        }
        return result;
    }

    /**
     * @description查询业务员
     * @author yangyun
     * @date 2019/1/15 0015
     * @param
     * @return java.util.List<com.exx.dzj.entity.user.UserInfo>
     */
    public List<UserInfo> querySalesman(){
        return salesmanService.querySalesman();
    }
}
