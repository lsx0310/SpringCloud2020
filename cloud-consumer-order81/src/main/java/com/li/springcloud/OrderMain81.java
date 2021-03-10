package com.li.springcloud;



import com.li.irule.MeRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


/**
 * @ClassName OrderMain81
 * @Description: TODO      关于Ribbon：  官方给出警告：自定义的配置类不能放在@ComponentScan所扫描的当前包下以及子包下
 *                                      否则我们自定义的这个配置类就会被所有的Ribbon客服端锁共享，不能达到特殊化定制的目的
 * @Author: li
 * @Date: 2020/5/17 18:20
 * @Version v1.0
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,})
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MeRule.class)
public class OrderMain81 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain81.class,args);
    }
}
