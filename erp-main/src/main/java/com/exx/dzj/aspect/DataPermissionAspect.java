package com.exx.dzj.aspect;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exx.dzj.annotation.DataPermission;
import com.exx.dzj.entity.datarules.DataPermissionRules;
import com.exx.dzj.entity.menu.MenuInfo;
import com.exx.dzj.facade.user.UserTokenFacade;
import com.exx.dzj.service.sys.DataPermissionRulesService;
import com.exx.dzj.service.sys.MenuService;
import com.exx.dzj.util.ConvertUtils;
import com.exx.dzj.util.DataAutorUtils;
import com.exx.dzj.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author
 * @Date 2019/5/27 0027 9:54
 * @Description 数据权限切面处理类
 *              当被请求的方法有注解 DataPermission 时,会在往当前request中写入数据权限信息
 */
@Slf4j
@Aspect
@Component
public class DataPermissionAspect {

    @Autowired
    private MenuService menuService;

    @Autowired
    private DataPermissionRulesService dataPermissionRulesService;

    @Autowired
    private UserTokenFacade userTokenFacade;

    /*@Autowired
    private UserFacade userFacade;

    @Autowired
    private UserService userService;*/

    @Pointcut("@annotation(com.exx.dzj.annotation.DataPermission)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object arround(ProceedingJoinPoint point) throws  Throwable{
        try {
            HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            DataPermission pd = method.getAnnotation(DataPermission.class);
            String component = pd.pageComponent();
            MenuInfo currentPermission = null;

            if(ConvertUtils.isNotEmpty(component)) {
                //1.通过注解属性 pageComponent 获取菜单
                LambdaQueryWrapper<MenuInfo> query = new LambdaQueryWrapper<MenuInfo>();
                query.eq(MenuInfo::getIsEnable,1);
                query.eq(MenuInfo::isHidden, false);
                query.eq(MenuInfo::getComponent, component);
                // 根据前端组件查询出权限数据(菜单数据)
                currentPermission = menuService.getOne(query);
            }else {
                String requestMethod = request.getMethod();
                String requestPath = request.getRequestURI().substring(request.getContextPath().length());
                requestPath = filterUrl(requestPath);
                log.info("拦截请求>>"+requestPath+";请求类型>>"+requestMethod);
                //1.直接通过前端请求地址查询菜单
                LambdaQueryWrapper<MenuInfo> query = new LambdaQueryWrapper<MenuInfo>();
                query.eq(MenuInfo::getMenuType,2);
                query.eq(MenuInfo::getIsEnable,1);
                query.eq(MenuInfo::getMenuUrl, requestPath);
                currentPermission = menuService.getOne(query);
                //2.未找到 再通过正则匹配获取菜单
                if(currentPermission==null) {
                    String regUrl = getRegexpUrl(requestPath);
                    if(regUrl!=null) {
                        currentPermission = menuService.getOne(new LambdaQueryWrapper<MenuInfo>().eq(MenuInfo::getMenuType,2).eq(MenuInfo::getMenuUrl, regUrl).eq(MenuInfo::getIsEnable,1));
                    }
                }
            }
            //3.通过用户名+菜单ID 找到权限配置信息 放到request中去
            if(currentPermission!=null) {
                //String username = JwtUtil.getUserNameByToken(request);
                /**
                 * 使用 userCode 查询是否会更好一点
                 */
                String userCode = userTokenFacade.queryUserCodeForToken(null);
                List<DataPermissionRules> dataRules = dataPermissionRulesService.queryDataPermissionRules(userCode, currentPermission.getMenuCode());
                if(dataRules!=null && dataRules.size()>0) {
                    DataAutorUtils.installDataSearchConditon(request, dataRules);

                    //TODO 此处将用户信息查找出来放到request中实属无奈  可以优化
                /*UserInfo userinfo = userService.queryUserInfo(username);
                DataAutorUtils.installUserInfo(request, userinfo);*/
                }
            }

            return  point.proceed();
        } catch(Exception ex) {
            log.error("异常信息:{}", ex.getMessage());
            return null;
        }
    }

    private String filterUrl(String requestPath){
        String url = "";
        if(ConvertUtils.isNotEmpty(requestPath)){
            url = requestPath.replace("\\", "/");
            url = requestPath.replace("//", "/");
            if(url.indexOf("//")>=0){
                url = filterUrl(url);
            }
			/*if(url.startsWith("/")){
				url=url.substring(1);
			}*/
        }
        return url;
    }

    /**
     * 获取请求地址
     * @param request
     * @return
     */
    private String getJgAuthRequsetPath(HttpServletRequest request) {
        String queryString = request.getQueryString();
        String requestPath = request.getRequestURI();
        if(ConvertUtils.isNotEmpty(queryString)){
            requestPath += "?" + queryString;
        }
        // 去掉其他参数(保留一个参数) 例如：loginController.do?login
        if (requestPath.indexOf("&") > -1) {
            requestPath = requestPath.substring(0, requestPath.indexOf("&"));
        }
        if(requestPath.indexOf("=")!=-1){
            if(requestPath.indexOf(".do")!=-1){
                requestPath = requestPath.substring(0,requestPath.indexOf(".do")+3);
            }else{
                requestPath = requestPath.substring(0,requestPath.indexOf("?"));
            }
        }
        // 去掉项目路径
        requestPath = requestPath.substring(request.getContextPath().length() + 1);
        return filterUrl(requestPath);
    }

    /**
     * 模糊查询
     * @param list
     * @param key
     * @return
     */
    private boolean moHuContain(List<String> list, String key){
        for(String str : list){
            if(key.contains(str)){
                return true;
            }
        }
        return false;
    }

    /**
     * 匹配前端传过来的地址 匹配成功返回正则地址
     * AntPathMatcher匹配地址
     *()* 匹配0个或多个字符
     *()**匹配0个或多个目录
     */
    private String getRegexpUrl(String url) {
        List<String> list = menuService.queryPermissionUrlWithStar();
        if(list!=null && list.size()>0) {
            for (String p : list) {
                PathMatcher matcher = new AntPathMatcher();
                if(matcher.match(p, url)) {
                    return p;
                }
            }
        }
        return null;
    }
}
