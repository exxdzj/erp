package com.exx.dzj.annotation;

import java.lang.annotation.*;

/**
 * @Author
 * @Date 2019/6/4 0004 14:13
 * @Description 作用于字段，用于标记查询是走 LIKE
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface LIKE {
}
