package com.li.springcloud.common.constants;

/**
 * @ClassName ServerName
 * @Description: TODO
 * @Author: li
 * @Date: 2021/3/10 16:14
 * @Version v1.0
 **/

public enum ServerName {
    
    /**
     * 网关服务名
     */
    GATEWAY("CLOUD-GATEWAY"),
    
    /**
     * 服务提供者
     */
    PAYMENT("CLOUD-PAYMENT"),
    
    /**
     * 服务消费者
     */
    CONSUMER("CLOUD-CONSUMER"),
    
    /**
     * WebSocket服务
     */
    WSAPP("CLOUD-WS-APPLICATION");
    
    
    
    private String severName;
    
    ServerName(String severName) {
        this.severName = severName;
    }
    
    public String getSeverName() {
        return severName;
    }
    
    public void setSeverName(String severName) {
        this.severName = severName;
    }
}
