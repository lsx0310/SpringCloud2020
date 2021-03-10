package com.li.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaMain7002
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/18 16:56
 * @Version v1.0
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaServer
public class EurekaMain7002 {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class,args);
    }
}
