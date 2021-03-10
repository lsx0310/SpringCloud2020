package com.li.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;

/**
 * @ClassName MyLogGateWayFilter
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/19 18:12
 * @Version v1.0
 **/
@Component
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("++++++++++come in mylog" + new Date());
    
        final URI uri = exchange.getRequest().getURI();
        
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
//        if (uname == null) {
//            System.out.println("非法用户"+uname);
//            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//            return exchange.getResponse().setComplete();
//        }
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return 0;
    }
}
