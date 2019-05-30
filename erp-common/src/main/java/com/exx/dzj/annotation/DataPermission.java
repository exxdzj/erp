package com.exx.dzj.annotation;

import java.lang.annotation.*;

/**
 * @Author
 * @Date 2019/5/27 0027 9:50
 * @Description 数据权限注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataPermission {

    /**
     * 权限规则
     * @return
     */
    String value() default "";

    /**
     * 配置菜单的组件路径,用于数据权限
     */
    String pageComponent() default "";
}
