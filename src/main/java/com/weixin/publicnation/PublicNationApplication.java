package com.weixin.publicnation;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.weixin.publicnation.utils.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = {"com.weixin.publicnation", "com.qhh.common"})
@EnableTransactionManagement
@MapperScan(basePackages = "com.weixin.publicnation.dao")
@SpringBootConfiguration
@EnableAutoConfiguration
public class PublicNationApplication {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        ConfigurableApplicationContext context = SpringApplication.run(PublicNationApplication.class, args);
        SpringContextUtils.setApplicationContext(context);
    }


}
