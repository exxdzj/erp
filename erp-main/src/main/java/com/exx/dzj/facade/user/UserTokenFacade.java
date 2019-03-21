package com.exx.dzj.facade.user;

import com.exx.dzj.service.user.UserTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author
 * @Date 2019/1/8 0008 16:40
 * @Description
 */
@Component
public class UserTokenFacade {

    @Autowired
    private UserTokenService tokenService;

    /**
     * 获取用户 user code
     * @return
     */
    public String queryUserCodeForToken(String userToken) {
        if(StringUtils.isEmpty(userToken)){
            return null;
        }
        HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        userToken = request.getHeader("user-token");
        return tokenService.queryUserCodeForToken(userToken);
    }
}
