package com.bree.ordermanagement.Controller;

import com.bree.inventorymanage.Entities.Product;
import com.bree.ordermanagement.Dto.OrderRequest;
import com.bree.ordermanagement.Entity.Order;
import com.bree.ordermanagement.Entity.OrderedItems;
import com.bree.ordermanagement.Service.OrderManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("ordered")
public class OrderController {

    private WebClient.Builder webclientBuilder;
    private final OrderManagementService orderManagementService;

    public OrderController(OrderManagementService orderManagementService) {
        this.orderManagementService = orderManagementService;
    }

    @PostMapping()
    public Mono<Order> placeOrder(@RequestBody OrderRequest orderRequest ){
        return orderManagementService.processOrder(orderRequest);
    }


    @GetMapping("/{orderId}")
    public Mono<Order> fetchCustomer(@PathVariable String orderId){
        return orderManagementService.customerOrder(orderId);
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Order> createOrder(@RequestBody Order order){
        return orderManagementService.createOrder(order);
    }

@GetMapping("/{orderId}/status")
    public Mono<String>getOrderStatus(@PathVariable String orderId){
        return orderManagementService.getOrderStatus(orderId);

}
@GetMapping()
    public Flux<OrderedItems>getAllItems(){
        return orderManagementService.getOrderedItems();
}
















/*

 @GetMapping("{orderId}")
    public Mono<Order>retrieveById(@PathVariable String orderId){
        return orderManagementService.retrieveOrderById(orderId);
    }


    @PutMapping("{orderId}")
    public Mono<Order> updateOrder(@PathVariable String orderId,@RequestBody Order order){
        return orderManagementService.updateOrder(orderId,order);
    }*/




}
