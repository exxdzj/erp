package com.exx.dzj.annotation;

import com.exx.dzj.constant.LogLevel;
import com.exx.dzj.constant.LogType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author
 * @Date 2019/5/15 0015 15:30
 * @Description 系统操作日志 注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLog {
    /**
     * 执行的操作
     * @return
     */
    String operate() default "";

    /**
     * 日志类型
     * @return 1:登录日志;2:操作日志;3:定时任务;
     */
    LogType logType() default LogType.LOG_TYPE_OPERATE;

    /**
     * 日志等级
     * @return error warn info dubug
     */
    LogLevel logLevel() default LogLevel.LOG_LEVEL_INFO;
}
