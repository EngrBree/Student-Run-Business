package com.bree.shippingmanagement.Client;

import com.bree.shippingmanagement.Entity.Order;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

public interface OrderManageClient {
    Mono<Order> getOrderById(String orderId);

}


