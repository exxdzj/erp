package com.exx.dzj.shiro.auth;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.share.TokenCache;
import com.exx.dzj.shiro.vo.DefContants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author
 * @Date 2019/5/7 0007 9:33
 * @Description 鉴权登录拦截
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private volatile static String type = null;

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
       try{
            executeLogin(request, response);
            return true;
       } catch (Exception e) {
            log.error("异常方法{}异常信息{}", JwtFilter.class.getName()+".isAccessAllowed", e.getMessage());
            //throw new AuthException(1001, "Token失效，请重新登录");
            try {
                if (StringUtils.equalsIgnoreCase(type, CommonConstant.ANDROID_TYPE)){
                    return true;
                }
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

        type = httpRequest.getHeader(DefContants.X_ACCESS_TYPE);

        String userCode = httpRequest.getHeader(DefContants.X_USER_CODE);

        TokenCache tokenCache = TokenCache.getTokenCache();
        Object o = null;
        if (StringUtils.equals(type, CommonConstant.ANDROID_TYPE)){
            o = tokenCache.getCache(type).get(userCode);
        } else {
            o = tokenCache.getCache(null).get(userCode);
        }

        if (!StringUtils.equals(token, o.toString())) {
            HttpServletResponse response1 = (HttpServletResponse) response;
            response1.setStatus(999);
            return false;
        }

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

    /**
     * 对跨域提供支持
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        return false;
    }
}
