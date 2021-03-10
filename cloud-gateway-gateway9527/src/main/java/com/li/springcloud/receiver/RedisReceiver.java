package com.li.springcloud.receiver;

import cn.hutool.core.lang.ConsistentHash;
import com.li.springcloud.hash.ConsistentHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ClassName RedisReceiver
 * @Description: TODO
 * @Author: li
 * @Date: 2021/3/10 15:41
 * @Version v1.0
 **/
@Component
public class RedisReceiver implements MessageListener {
    
    @Autowired
    ConsistentHashing consistentHashing;
    
    
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //根据频道的信息判断是执行put 还是 remove
        
    }
}
