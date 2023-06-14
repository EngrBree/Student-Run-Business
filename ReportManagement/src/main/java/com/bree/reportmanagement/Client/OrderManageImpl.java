package com.bree.reportmanagement.Client;

import com.bree.reportmanagement.Model.Order;
import com.bree.reportmanagement.Model.OrderItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Component
public class OrderManageImpl implements OrderManageClient{
 private final WebClient webClient;

    public OrderManageImpl(@Value("${OrderManagement.https://localhost:8081}")String orderManageUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(orderManageUrl)
                .build();
    }

    @Override
    public Flux<Order> getAllOrders() {
        return webClient.get()
                .uri("/ordered/{orderId}")
                .retrieve()
                .bodyToFlux(Order.class);
    }

    @Override
    public Flux<OrderItem> getOrderItems() {
        return webClient.get()
                .uri("/orders/{orderId}/items")
                .retrieve()
                .bodyToFlux(OrderItem.class);
    }


}
