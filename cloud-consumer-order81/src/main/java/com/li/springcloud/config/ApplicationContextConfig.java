package com.li.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName AplicationContextConfig
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/17 18:26
 * @Version v1.0
 **/
@Configuration
public class ApplicationContextConfig {
    
    /**
     * @Description:   @LoadBalanced  赋予RestTemplate负载均衡的能力
     * @Author: Li
     * @Date 2020/5/18 17:48
     * @return: org.springframework.web.client.RestTemplate
     **/
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
