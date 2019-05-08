package com.exx.dzj.shiro.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author
 * @Date 2019/5/7 0007 11:15
 * @Description 鉴权请求URL访问权限拦截器
 */
@Slf4j
public class ResourceCheckFilter extends AccessControlFilter {

    private String errorUrl;

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    /**
     * 表示是否允许访问 ，如果允许访问返回true，否则false；
     * @param request
     * @param response
     * @param mappedValue 表示写在拦截器中括号里面的字符串 mappedValue 就是 [urls] 配置中拦截器参数部分
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String url = getPathWithinApplication(request);
        log.info("当前用户正在访问的 url:{}", url);
        return subject.isPermitted(url);
    }

    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了
     * 如果返回 true 表示需要继续处理
     * 如果返回 false 表示该拦截器实例已经处理了，将直接返回即可
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("当 isAccessAllowed 返回 false 的时候，才会执行 method onAccessDenied ");

        HttpServletRequest httpRequest =(HttpServletRequest) request;
        HttpServletResponse httpResponse =(HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + this.errorUrl);
        // 返回 false 表示已经处理，例如页面跳转啥的，表示不在走以下的拦截器了（如果还有配置的话）
        return false;
    }
}
