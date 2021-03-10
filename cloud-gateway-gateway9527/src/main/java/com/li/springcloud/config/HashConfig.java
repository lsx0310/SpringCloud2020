package com.li.springcloud.config;

import com.li.springcloud.common.constants.ServerRedisKey;
import com.li.springcloud.hash.ConsistentHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName HashConfig
 * @Description: TODO
 * @Author: li
 * @Date: 2021/3/10 18:17
 * @Version v1.0
 **/
@Configuration
public class HashConfig {
    
    @Autowired
    RedisTemplate redisTemplate;
    
    
    @Bean
    ConsistentHashing consistentHashing() {
        return new ConsistentHashing(true) {
            @Override
            protected void initNodes() {
                List<String> range = (List<String>) redisTemplate.opsForList().range(ServerRedisKey.WS_SERVERS_ADDR, 0, -1);
                assert range != null;
                if (range.isEmpty()) {
                    return;
                }
                for (String next : range) {
                    this.put(next);
                }
            }
        };
    }
}
