package com.li.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @ClassName WebSocketConfig
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/18 12:09
 * @Version v1.0
 **/
@Configuration
//@EnableWebSocketMessageBroker   启用SockJs协议
public class WebSocketConfig {
    
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    
}