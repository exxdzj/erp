package com.exx.dzj.aspect;

import com.alibaba.fastjson.JSONObject;
import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.LogLevel;
import com.exx.dzj.entity.syslog.LogBean;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.facade.log.SysLogFacade;
import com.exx.dzj.util.IPUtils;
import com.exx.dzj.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author
 * @Date 2019/5/15 0015 16:11
 * @Description 系统日志
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogFacade sysLogFacade;

    /**
     * @功能: 切点
     */
    @Pointcut("@annotation(com.exx.dzj.annotation.SysLog)")
    public void sysLogPointCut() {}

    /**
     * @功能: 环绕通知
     * @param point 环绕通知的连接点参数类型必须是 ProceedingJoinPoint，它是 JoinPoint 的子类
     * @return
     * @throws Throwable
     */
    @Around("sysLogPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //开始时间
        long beginTime = System.currentTimeMillis();
        //执行方法，需要明确的调用proceed()方法来执行被代理方法,否则会导致通知被执行，但是目标方法没有执行
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        saveLog(point, time, null);
        return result;
    }

    /**
     * @功能: 后置异常通知
     * @param point
     * @param e
     * @描述: 修改方法、保存方法最好是抛出,这样有利于日志表记录异常信息
     */
    @AfterThrowing(pointcut = "sysLogPointCut()", throwing = "e")
    public void throwss(JoinPoint point, Throwable e){
        saveLog(point, 0L, e);
    }

    /**
     * @功能: 记录日志
     * @param point
     * @param time
     * @注意: 方法中的参数最好是对象，这样有利于后期日志管理显示
     */
    private void saveLog(JoinPoint point, long time, Throwable t) {
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();

            LogBean logBean = new LogBean();
            SysLog syslog = method.getAnnotation(SysLog.class);
            if(syslog != null){
                //注解上的数据
                logBean.setLogType(syslog.logType().getValue());
                logBean.setLogLevel(syslog.logLevel().getValue());
                logBean.setLogContent(syslog.operate());
                logBean.setOperate(syslog.operate());
            }

            if(null != t) {
                logBean.setLogContent(t.getMessage());
                logBean.setLogLevel(LogLevel.LOG_LEVEL_ERROR.getValue());
            }

            //请求的方法名
            String className = point.getTarget().getClass().getName();
            String methodName = signature.getName();
            logBean.setMethod(className + "." + methodName + "()");

            //请求的参数
            Object[] args = point.getArgs();
            if(null != args && args.length > 0) {
                String params = JSONObject.toJSONString(args);
                logBean.setParams(params);
            }

            //获取request
//            HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
//            if (request != null){
//                //设置IP地址
//                logBean.setNode(IPUtils.getIpAddr(request));
//            }

            //获取登录用户信息
            UserInfo userInfo = (UserInfo)SecurityUtils.getSubject().getPrincipal();
            if(userInfo!=null){
                logBean.setCreateUser(userInfo.getUserCode());
                logBean.setUserName(userInfo.getRealName());
            }
            //耗时
            logBean.setCostTime(time);

            //保存日志
            sysLogFacade.saveSysLog(logBean);
        } catch(Exception ex) {
            ex.printStackTrace();
            log.error("执行方法:{}错误信息:{}", SysLogAspect.class.getName()+".saveLog", ex.getMessage());
        }
    }

}
