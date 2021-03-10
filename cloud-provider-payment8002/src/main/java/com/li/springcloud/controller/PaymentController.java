package com.li.springcloud.controller;

import com.li.springcloud.common.response.Result;
import com.li.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.li.springcloud.service.impl.PaymentServiceImpl;

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
    
    @GetMapping("/payment/lb")
    public String getPort() {
        return serverPort;
    }
}
