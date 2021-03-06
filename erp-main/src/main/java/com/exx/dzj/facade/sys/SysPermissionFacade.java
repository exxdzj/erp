package com.exx.dzj.facade.sys;

import com.alibaba.fastjson.JSONObject;
import com.exx.dzj.facade.user.UserTokenFacade;
import com.exx.dzj.service.sys.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Date 2019/3/22 0022 14:51
 * @Description 系统权限
 */
@Slf4j
@Component
public class SysPermissionFacade {

    @Autowired
    private UserTokenFacade userTokenFacade;

    @Autowired
    private SysPermissionService sysPermissionService;

    public JSONObject queryPermissionsByUser(String userToken){
        String userCode = userTokenFacade.queryUserCodeForToken(userToken);
        return sysPermissionService.queryPermissionsByUser(userCode);
    }
}
