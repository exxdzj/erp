package com.exx.dzj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 开启事务
@ComponentScan("com.exx.dzj")
@MapperScan("com.exx.dzj.mapper") // mapper 包扫描
@EnableCaching //开启基于注解的缓存
public class ErpMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpMainApplication.class, args);
    }

}

