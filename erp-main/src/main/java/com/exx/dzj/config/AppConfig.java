package com.exx.dzj.config;

import com.exx.dzj.interceptors.ProcessInterceptor;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

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

    @Bean
    public MultipartConfigElement multipartConfigFactory (){

        MultipartConfigFactory factory = new MultipartConfigFactory();

        factory.setMaxFileSize("102400KB");
        factory.setMaxRequestSize("1024000KB");

        return factory.createMultipartConfig();
    }
}
