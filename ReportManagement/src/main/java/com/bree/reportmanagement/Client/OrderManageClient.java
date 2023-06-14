package com.bree.reportmanagement.Client;

import com.bree.reportmanagement.Model.Order;
import com.bree.reportmanagement.Model.OrderItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderManageClient {
    Flux<Order>getAllOrders();
    Flux<OrderItem>getOrderItems();
}
