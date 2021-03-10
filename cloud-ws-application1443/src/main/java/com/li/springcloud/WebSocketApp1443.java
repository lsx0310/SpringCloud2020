package com.li.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @ClassName WebSocketApp1443
 * @Description: TODO
 * @Author: li
 * @Date: 2021/3/10 14:12
 * @Version v1.0
 **/

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
@EnableWebSocket
public class WebSocketApp1443 {
    
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApp1443.class,args);
    }
}
