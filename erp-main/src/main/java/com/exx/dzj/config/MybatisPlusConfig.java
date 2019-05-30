package com.exx.dzj.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author
 * @Date 2019/5/29 0029 8:40
 * @Description MybatisPlus 的配置
 */
@Configuration
@MapperScan("com.exx.dzj.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件，自动识别数据库类型
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
