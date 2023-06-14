package com.bree.shippingmanagement.Client;

import com.bree.shippingmanagement.Entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
public class orderManagementWebclient implements OrderManageClient{
private final WebClient webClient;

    public orderManagementWebclient(@Value("${OrderManagement.http://localhost:8081/OrderManagement}") String orderManagementUrl ){

        this.webClient = WebClient.builder()
                .baseUrl(orderManagementUrl)
                .build();
    }
@Override
    public Mono<Order> getOrderById(String orderId) {
        return webClient.get()
                .uri("ordered/{orderId}",orderId)
                .retrieve()
                .bodyToMono(Order.class);
    }
}
