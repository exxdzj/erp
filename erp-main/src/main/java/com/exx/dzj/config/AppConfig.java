package com.exx.dzj.config;

import com.exx.dzj.interceptors.ProcessInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName:
 * @Description: spring mvc 功能实现
 * @author 杨云
 * @date 下午4:44:04
 */
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    /**@Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter () {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            // 注册自己的拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new ProcessInterceptor());
            }
        };

        return adapter;
    }*/
}
