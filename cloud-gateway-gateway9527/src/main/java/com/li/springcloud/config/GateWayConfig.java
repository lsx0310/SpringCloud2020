package com.li.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName GateWayConfig
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/19 17:16
 * @Version v1.0
 **/
@Configuration
public class GateWayConfig {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_li",r -> r.path("/guonei").uri("http://news.baidu.com")).build();
        return routes.build();
    }
}
