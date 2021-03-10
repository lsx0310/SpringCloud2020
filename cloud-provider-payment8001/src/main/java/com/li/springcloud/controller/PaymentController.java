package com.li.springcloud.controller;

import com.li.springcloud.common.response.Result;
import com.li.springcloud.entity.Payment;
import com.li.springcloud.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PaymentController
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/17 18:46
 * @Version v1.0
 **/
@RestController
public class PaymentController {
    
    @Autowired
    private PaymentServiceImpl paymentService;
    @Value("${server.port}")
    private String serverPort;
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @PostMapping("/payment")
    public Result create(@RequestBody Payment payment) {
        return paymentService.save(payment) ? Result.SUCCESS(payment) : Result.FAIL();
    }
    
    @GetMapping("/payment/get/{id}")
    public Result getPaymentById(@PathVariable("id")Long id) {
        Payment payment =  paymentService.getById(id);
        if (payment == null) {
            Result.FAIL();
        }
        System.out.println("serverPort" + serverPort);
        return Result.SUCCESS(payment);
    }
    
    @GetMapping("/payment/discover")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element: services) {
            System.out.println(element + "******element");
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getUri());
        }
        
        return this.discoveryClient;
    }
    
    @GetMapping("/payment/lb")
    public String getPort() {
        return serverPort;
    }
}
