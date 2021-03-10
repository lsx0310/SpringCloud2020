package com.li.springcloud.rule;

import com.li.springcloud.lb.LoadBalancer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName MyRUule
 * @Description: TODO
 * @Author: li
 * @Date: 2021/3/9 16:55
 * @Version v1.0
 **/
public class MyRule extends AbstractLoadBalancerRule {
    
    
    
    
    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        System.out.println("myRUle.....initWithNiwsConfig");
    }
    
    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(),key);
    }
    
    
    public Server choose(ILoadBalancer lb, Object key) {
        System.out.println("自定义的Rule");
        if (lb == null) {
            return null;
        }
        Server server = null;
    
        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();
        
            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }
        
            int index = chooseRandomInt(serverCount);
            server = upList.get(index);
        
            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }
        
            if (server.isAlive()) {
                return (server);
            }
        
            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }
    
        return server;
    }
    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }
}
