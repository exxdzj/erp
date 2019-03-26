package com.exx.dzj.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @description 自定义缓存 keyGenerator
 * @author yangyun
 * @date 2019/3/22 0022
 * @return
 */

@Configuration
public class CacheConfig {

    @Bean("myKeyGenerator")
    public KeyGenerator getKeyGenerator (){
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                // 方法名 + 参数数组 getEmp[id]
                return method.getName() + Arrays.asList(objects).toString();
            }
        };
    }
}
