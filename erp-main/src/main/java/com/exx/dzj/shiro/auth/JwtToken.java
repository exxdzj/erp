package com.exx.dzj.shiro.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author
 * @Date 2019/5/7 0007 9:53
 * @Description
 */
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    /**
     * 返回在身份验证过程中提交的帐户标识
     * @return
     */
    @Override
    public Object getPrincipal() {
        return token;
    }

    /**
     * 在身份验证过程中返回用户提交的凭据
     * @return
     */
    @Override
    public Object getCredentials() {
        return token;
    }
}
