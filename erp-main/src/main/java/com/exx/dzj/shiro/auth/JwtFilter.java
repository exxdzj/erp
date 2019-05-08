package com.exx.dzj.shiro.auth;

import com.exx.dzj.shiro.excepte.AuthException;
import com.exx.dzj.shiro.vo.DefContants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author
 * @Date 2019/5/7 0007 9:33
 * @Description 鉴权登录拦截
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 执行登录认证
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            executeLogin(request, response);
            return true;
        } catch (AuthException e) {
            log.error("异常方法{}异常信息{}", JwtFilter.class.getName()+".isAccessAllowed", e.getMessage());
            //throw new AuthException(1001, "Token失效，请重新登录");
            try {
                response.getWriter().write("{\"code\":\"1001\",\"msg\":\"登录过期\"}");
            } catch(Exception ex) {

            }
            //throw new AuthenticationException("Token失效，请重新登录", e);
            return false;
        }
    }

    /**
     * 登录需要执行的方法
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        /**
         * 获取 user-token
         */
        String token = httpRequest.getHeader(DefContants.X_ACCESS_TOKEN);
        JwtToken jwtToken = new JwtToken(token);
        /**
         * 提交给realm进行登入，如果错误他会抛出异常并被捕获
         */
        getSubject(request, response).login(jwtToken);
        /**
         * 如果没有抛出异常则代表登入成功，返回true
         */
        return true;
    }
}
