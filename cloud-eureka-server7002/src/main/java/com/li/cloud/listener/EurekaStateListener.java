package com.li.cloud.listener;


import com.li.springcloud.common.constants.ServerName;
import com.li.springcloud.common.constants.ServerRedisKey;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @ClassName listener
 * @Description: TODO
 * @Author: li
 * @Date: 2021/3/10 15:49
 * @Version v1.0
 **/
@Component
public class EurekaStateListener {
    
    @Autowired
    RedisTemplate redisTemplate;
    
    /**
     * 监控下线的服务
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        System.out.println(event.getAppName() + "服务下线了");
    }
    
    /**
     * 监控注册中心启动
     * @param event
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.out.println("Eureka 注册中心启动");
    }
    
    /**
     * 监听服务注册
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        String serverName = instanceInfo.getAppName();
        if (ServerName.WSAPP.getSeverName().equals(serverName)) {
            String ipAddr = instanceInfo.getIPAddr();
            int port = instanceInfo.getPort();
            
//            redisTemplate.convertAndSend(ServerRedisKey.WS_CHANNEL_TOPIC,);
        }
    
        System.out.println(serverName + "服务进行注册");
        System.out.println("服务名：" + serverName);
        System.out.println("服务id" + instanceInfo.getId());
        System.out.println("服务状态" + instanceInfo.getStatus());
        System.out.println("服务VIP地址" + instanceInfo.getVIPAddress());
        System.out.println("服务元数据" + instanceInfo.getMetadata());
        System.out.println(instanceInfo.getActionType());
        System.out.println(instanceInfo.getAppGroupName());
        System.out.println(instanceInfo.getHealthCheckUrl());
        System.out.println(instanceInfo.getInstanceId());
        System.out.println(instanceInfo.getHealthCheckUrls());
    }
    
    /**
     * 监听服务续约
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        System.out.println(event.getAppName() + "服务进行续约");
    }
    
    /**
     * 监听服务启动
     * @param event
     */
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        System.out.println("Eureka Server启动");
    }
}
