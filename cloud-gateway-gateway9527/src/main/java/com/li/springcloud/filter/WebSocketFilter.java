package com.li.springcloud.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.cloud.gateway.filter.WebsocketRoutingFilter;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

/**
 * @ClassName WebSocketFilter
 * @Description: TODO
 * @Author: li
 * @Date: 2021/3/9 23:27
 * @Version v1.0
 **/
@Component
public class WebSocketFilter extends ReactiveLoadBalancerClientFilter {
    
    private static final Log log = LogFactory.getLog(WebSocketFilter.class);
    
    public WebSocketFilter(LoadBalancerClientFactory clientFactory, LoadBalancerProperties properties) {
        super(clientFactory, properties);
    }
    
    @Override
    public int getOrder() {
        return super.getOrder();
    }
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI requestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);
        String scheme = requestUrl.getScheme();
        String schemePrefix = exchange.getAttribute(GATEWAY_SCHEME_PREFIX_ATTR);
        if (isAlreadyRouted(exchange)
                || (!"ws".equals(scheme) && !"wss".equals(scheme))
                || (!"lb".equals(schemePrefix))) {
            return super.filter(exchange,chain);
        }
    
        /**
         * 实现ws的负载均衡算法
         */
        System.out.println(schemePrefix);
        System.out.println(requestUrl);
        System.out.println("ws的请求被修改了");
        System.out.println(requestUrl.getQuery());
        System.out.println(requestUrl.getRawQuery());
        return chain.filter(exchange);
    }
    
    
    
}
