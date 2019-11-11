package com.exx.dzj.annotation;

import java.lang.annotation.*;

/**
 * @Author
 * @Date 2019/5/15 0015 15:31
 * @Description 销售单操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SaleLog {
    /**
     * 执行的操作
     * @return
     */
    String operate() default "";

    String saleCode() default "";
}
