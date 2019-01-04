package com.exx.dzj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableTransactionManagement
//@ComponentScan("com.exx.dzj")
//@MapperScan("com.exx.dzj")
public class ErpMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpMainApplication.class, args);
	}

}

