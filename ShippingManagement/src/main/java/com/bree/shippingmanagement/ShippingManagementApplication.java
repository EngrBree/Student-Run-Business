package com.bree.shippingmanagement;

import com.bree.shippingmanagement.Client.OrderManageClient;
import com.bree.shippingmanagement.Entity.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.bree.shippingmanagement.Client")

public class ShippingManagementApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShippingManagementApplication.class, args);



    }

    @Bean

    public WebClient webClient(){
        return WebClient.builder().build();
    }

}
