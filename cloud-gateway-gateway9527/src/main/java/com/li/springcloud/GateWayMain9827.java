package com.li.springcloud;


import irule.MeRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * @ClassName GateWayMain9827
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/19 16:00
 * @Version v1.0
 **/
@SpringBootApplication
@EnableEurekaClient
//@RibbonClients(value = @RibbonClient (
//        name = "CLOUD-PAYMENT-SERVICE",configuration = MeRule.class
//))
public class GateWayMain9827 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain9827.class,args);
    }
}
