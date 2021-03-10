package com.li.springcloud.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @ClassName WebSocketServer
 * @Description: TODO
 * @Author: li
 * @Date: 2021/3/10 14:24
 * @Version v1.0
 **/
@Component
@ServerEndpoint("/ws/{uid}")
public class WebSocketServer {


    @OnOpen
    public void onOpen(Session session,@PathParam("uid")String uid) throws IOException {
        session.getBasicRemote().sendText("收到上线请求");
    }
    
    
    @OnClose
    public void onClose(Session session) {
    
    }
    
    
    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
    }
    
    
    @OnError
    public void onError(Session session,Throwable throwable)  {
    
    }

    
    
}
