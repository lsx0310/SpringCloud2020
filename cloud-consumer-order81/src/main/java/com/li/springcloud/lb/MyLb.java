package com.li.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MyLb
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/18 19:42
 * @Version v1.0
 **/
@Component
public class MyLb implements LoadBalancer{
    
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    
    
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        System.out.println("调用自定义的Rule....");
        int len = serviceInstances.size();
        int index = getAndIncrement()  % len;
        return serviceInstances.get(index);
    }
    
    
    public final int getAndIncrement() {
        int current;
        int next;
        for (;;) {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            if (this.atomicInteger.compareAndSet(current,next)) {
                return next;
            }
        }
    }
}
