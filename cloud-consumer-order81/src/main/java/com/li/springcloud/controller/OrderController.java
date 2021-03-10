package com.li.springcloud.controller;

import com.li.springcloud.common.response.Result;
import com.li.springcloud.entity.Payment;
import com.li.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

/**
 * @ClassName OrderController
 * @Description: TODO   getForObject 返回对象为响应体中数据转化成的对象，基本可以理解为json
 *                      getForEntity 返回对象为ResponseEntity对象，包含了响应中的一些重要信息，如响应头、响应状态码，响应体等
 * @Author: li
 * @Date: 2020/5/17 18:29
 * @Version v1.0
 **/
@RestController
public class OrderController {
    
    @Resource
    private RestTemplate restTemplate;
    
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    
    @Autowired
    private LoadBalancer loadBalancer;
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @PostMapping("/consumer/payment")
    public Result create(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment",payment,Result.class);
    }
    
    @GetMapping("/consumer/payment/get/{id}")
    public Result getPaymentById(@PathVariable("id")Long id) {
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/" + id,Result.class);
    }
    
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLb() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
        
    }
}
