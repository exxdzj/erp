package com.exx.dzj.shiro.util;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.user.UserVo;
import com.exx.dzj.facade.user.UserFacade;
import com.exx.dzj.shiro.auth.JwtToken;
import com.exx.dzj.shiro.excepte.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * @Author
 * @Date 2019/5/7 0007 14:30
 * @Description
 */
@Slf4j
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserFacade userFacade;

    /**
     * 必须重写此方法，不然Shiro会报错
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     * 获取授权信息 Shiro中，只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("权限认证-执行方法{}", MyRealm.class.getName()+".doGetAuthorizationInfo");
        UserVo userVo = null;
        String userName = null;
        if(null != principals) {
            userVo = (UserVo)principals.getPrimaryPrincipal();
            userName = userVo.getUserName();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 可以通过用户信息去查询用户角色和用户权限

        /**
         * 设置该用户拥有的角色，比如“admin,test”
         */
        info.setRoles(new HashSet<>(userVo.getRoles()));
        return info;
    }

    /**
     * 获取身份验证信息 Shiro中，默认使用此方法进行用户名正确与否验证，错误抛出异常即可
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        log.debug("身份认证方法,方法{}", MyRealm.class.getName()+".doGetAuthenticationInfo");
        String token = (String)auth.getCredentials();
        if (token == null) {
            //throw new AuthenticationException("token为空!");
            throw new AuthException(1002, "token为空!");
        }

        // 解密获得userName，用于和数据库进行对比
        String userName = JwtUtil.getUserName(token);
        if (userName == null) {
            //throw new AuthenticationException("token非法无效!");
            throw new AuthException(1003, "token非法无效!");
        }

        // 查询用户信息
        UserVo userVo = userFacade.queryUserName(userName);
        if(null == userVo) {
            //throw new AuthenticationException("用户不存在!");
            throw new AuthException(1004, "用户不存在!");
        }

        // 如果有缓存，则需要校验token是否超时失效 & 或者账号密码是否错误
        if(!JwtUtil.verify(token, userName, userVo.getPassWord())) {
            //throw new AuthenticationException("用户名或密码错误!");
            throw new AuthException(1005, "账号登录已失效!");
        }

        // 账号被封号或者用户已离职
        if(userVo.getLoginStatus() == CommonConstant.DEFAULT_VALUE_TWO || userVo.getIsQuit() == CommonConstant.DEFAULT_VALUE_ZERO) {
            //throw new AuthenticationException("账号已被封号或者用户已离职,请联系管理员!");
            throw new AuthException(1006, "账号已被封号或者用户已离职,请联系管理员!");
        }

        return new SimpleAuthenticationInfo(userVo, token, getName());
    }
}
