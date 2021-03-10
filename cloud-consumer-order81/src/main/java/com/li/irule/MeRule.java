package com.li.irule;

import com.li.springcloud.lb.MyLb;
import com.li.springcloud.rule.MyRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName IRule
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/18 19:11
 * @Version v1.0
 **/
@Configuration
public class MeRule {

    @Bean
    public IRule iRule(){
        return new MyRule();
    }

}
