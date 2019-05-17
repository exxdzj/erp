package com.exx.dzj.aspect;

import com.alibaba.fastjson.JSONObject;
import com.exx.dzj.annotation.SaleLog;
import com.exx.dzj.entity.salelog.SaleLogBean;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.facade.log.SaleLogFacade;
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

import java.lang.reflect.Method;

/**
 * @Author
 * @Date 2019/5/15 0015 16:11
 * @Description  销售单日志
 */
@Slf4j
@Aspect
@Component
public class SaleLogAspect {

    @Autowired
    private SaleLogFacade saleLogFacade;

    /**
     * @功能: 切点
     */
    @Pointcut("@annotation(com.exx.dzj.annotation.SaleLog)")
    public void saleLogPointCut() {

    }

    /**
     * @功能: 环绕通知
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("saleLogPointCut()")
    public Object arount(ProceedingJoinPoint point) throws Throwable{
        Object result = point.proceed();
        saveSaleLog(point, null);
        return result;
    }

    /**
     * @功能: 异常通知
     * @param point
     * @param e
     */
    @AfterThrowing(pointcut="saleLogPointCut()", throwing = "e")
    public void throwss(JoinPoint point, Throwable e) {
        saveSaleLog(point, e);
    }

    /**
     * @功能: 获取到相关切面函数的信息，并且保存到日志表
     * @param point
     * @param t
     */
    private void saveSaleLog(JoinPoint point, Throwable t) {
        try {
            /**
             * 获取方法签名(即：切面方法的全限定名)
             */
            MethodSignature signature = (MethodSignature)point.getSignature();
            Method method = signature.getMethod();

            SaleLogBean saleLogBean = new SaleLogBean();
            SaleLog saleLog = method.getAnnotation(SaleLog.class);
            if(null != saleLog) {
                saleLogBean.setOperate(saleLog.operate());
                saleLogBean.setLogContent(saleLog.operate());
            }

            if(null != t) {
                saleLogBean.setLogContent(t.getMessage());
            }

            //请求方法
            String className = point.getTarget().getClass().getName();
            String methodName = signature.getName();
            saleLogBean.setMethod(className + "." +methodName + "()");

            Object[] args = point.getArgs();
            try{
                String params = JSONObject.toJSONString(args);
                saleLogBean.setParams(params);
            }catch (Exception e){
                log.error("参数解析错误,执行方法:{},异常信息:{}", SaleLogAspect.class.getName()+".saveLog", e.getMessage());
            }

            //获取登录用户信息
            UserInfo userInfo = (UserInfo)SecurityUtils.getSubject().getPrincipal();
            if(userInfo!=null){
                saleLogBean.setCreateUser(userInfo.getUserCode());
                saleLogBean.setUserName(userInfo.getRealName());
            }

            saleLogFacade.saveSaleLog(saleLogBean);
        } catch(Exception ex) {
            log.error("执行方法:{}错误信息:{}", SaleLogAspect.class.getName()+".saveLog", ex.getMessage());
        }
    }
}
