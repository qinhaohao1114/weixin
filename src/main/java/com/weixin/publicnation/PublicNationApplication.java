package com.weixin.publicnation;

import com.weixin.publicnation.utils.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.weixin.publicnation.dao")
public class PublicNationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PublicNationApplication.class, args);
        SpringContextUtils.setApplicationContext(context);
    }

}
