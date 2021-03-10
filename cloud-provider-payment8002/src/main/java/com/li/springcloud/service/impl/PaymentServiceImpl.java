package com.li.springcloud.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.springcloud.entity.Payment;

import com.li.springcloud.mapper.PaymentMapper;
import com.li.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

/**
 * @ClassName PaymentImpl
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/17 17:48
 * @Version v1.0
 **/
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
}
