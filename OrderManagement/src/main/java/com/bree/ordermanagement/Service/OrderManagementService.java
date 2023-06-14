package com.bree.ordermanagement.Service;
import com.bree.ordermanagement.Repository.orderedItemsRepository;
import com.bree.customermanage.Model.Customer;
import com.bree.ordermanagement.Client.ShipmentManageImpl;
import com.bree.ordermanagement.Dto.OrderRequest;
import com.bree.ordermanagement.Dto.OrderedItemsDto;
import com.bree.ordermanagement.Entity.Order;
import com.bree.ordermanagement.Entity.OrderedItems;
import com.bree.ordermanagement.Entity.Shipping;
import com.bree.ordermanagement.Entity.ShippingStatus;
import com.bree.ordermanagement.Repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderManagementService {
    private final WebClient inventoryWebClient;
    private final WebClient paymentWebClient;
    private final WebClient customerWebclient;
    private final OrderRepository orderRepository;
    private final orderedItemsRepository orderedItemsRepository;

    public OrderManagementService(OrderRepository orderRepository, WebClient.Builder webClientBuilder, com.bree.ordermanagement.Repository.orderedItemsRepository orderedItemsRepository) {
        this.orderRepository = orderRepository;
       this.inventoryWebClient = webClientBuilder.baseUrl("http://localhost:8080/InventoryManage").build();
        this.paymentWebClient = webClientBuilder.baseUrl("http://localhost:8083/PaymentProcessingManage").build();
        this.customerWebclient=webClientBuilder.baseUrl("http://localhost:8082/CustomerManage").build();
        this.orderedItemsRepository = orderedItemsRepository;
    }
    public Mono<Order> processOrder(OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderId(UUID.randomUUID().toString());
        List<OrderedItems> orderedItems = new ArrayList<>();
        List<OrderedItemsDto> orderedItemsDtoList = orderRequest.getOrderedItemsDtoList();
        if (orderedItemsDtoList != null && !orderedItemsDtoList.isEmpty()) {
           orderedItems=orderedItemsDtoList.stream()
                   .map(orderedItemsDto -> mapToDto(orderedItemsDto))
                   .collect(Collectors.toList());
        } else {
            orderedItems = new ArrayList<>();
        }
        List<OrderedItems> finalOrderedItems = orderedItems;
        return isProductAvailable(orderRequest)
                .flatMap(inventoryAvailability -> {
                    if (inventoryAvailability) {
                        return processPayment(orderRequest)
                                .flatMap(paymentStatus -> {
                                    if (paymentStatus.equals("SUCCESS")) {
                                       order.setOrderedItemsList(finalOrderedItems);
                                        return Mono.just(order);
                                       // return Mono.empty(); // Order processing successful
                                    } else {
                                        return Mono.error(new IllegalArgumentException("Payment failed"));
                                    }
                                });
                    } else {
                        return Mono.error(new IllegalArgumentException("Product not available"));
                    }
                });

    }
    private Mono<Boolean> isProductAvailable(OrderRequest orderRequest) {
        List<OrderedItemsDto> orderedItemsDtoList = orderRequest.getOrderedItemsDtoList();
        if (orderedItemsDtoList != null && !orderedItemsDtoList.isEmpty()) {
        OrderedItemsDto firstItem = orderRequest.getOrderedItemsDtoList().get(0);
        return inventoryWebClient
                .post()
                .uri("/products/{productid}/availability",firstItem.getProductId())
                .bodyValue(orderRequest.getOrderedItemsDtoList())
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(throwable->{
                    return Mono.just(false);
                });
        }
        else{
            return Mono.error(new IllegalArgumentException("No ordered items found"));
        }
    }

    private Mono<String> processPayment(OrderRequest orderRequest) {

        return paymentWebClient
                .post()
                .uri("/payments/{paymentId}")
                .bodyValue(orderRequest)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(throwable -> {
                    return Mono.just("FAILURE");
                });

    }
    private OrderedItems mapToDto(OrderedItemsDto OrderedItems){
        OrderedItems orderedItems=new OrderedItems();
        OrderedItems.setId(orderedItems.getId());
        OrderedItems.setName(orderedItems.getName());
        OrderedItems.setPrice(orderedItems.getPrice());
        OrderedItems.setQuantity(orderedItems.getQuantity());
        OrderedItems.setProductId(orderedItems.getProductId());
        OrderedItems.setOrderId(orderedItems.getOrderId());

        return orderedItems;
    }
    public  Mono<Order> retrieveOrderById(String orderId){
        return orderRepository.findById(orderId);
    }
    public Flux<Order> retrieveAllOrders(){
        return orderRepository.findAll();
    }
    public Mono<Order> customerOrder(String orderId) {
        return fetchCustomerOrder(orderId);
    }

    public Mono<Order> fetchCustomerOrder(String orderId) {
        Mono<Order> orderMono = orderRepository.findById(orderId);
        return orderMono.flatMap(customerOrder -> {
            String customerId = customerOrder.getCustomerId();
            String ShippingAddress=customerOrder.getCustomerAddress();
            return customerWebclient.get()
                    .uri("/customers/{customerId}", customerId)
                    .retrieve()
                    .bodyToMono(Customer.class)
                    .map(customer -> {
                        customerOrder.setCustomer(customer);
                        return customerOrder;
                    });

        });
    }

    public Mono<String>getOrderStatus(String orderId){
        //return orderRepository.findById(orderId)
        Mono<Order> orderMono = orderRepository.findById(orderId);
        //ShipmentManageImpl shipmentManage=new ShipmentManageImpl();
        ShipmentManageImpl shipmentManage=new ShipmentManageImpl("\"${ShippingManagement.http://localhost:8081}");
        Mono<Shipping> shippingStatusMono = shipmentManage.updateStatus(orderId);

        return Mono.zip(orderMono,shippingStatusMono)
                .flatMap(tuple->{
                    Order order=tuple.getT1();
                    ShippingStatus shippingStatus=tuple.getT2().getStatus();
                    order.setShippingStatus(shippingStatus);
                    String overallOrderStatus = calculateOverallOrderStatus(order);

                    return Mono.just(overallOrderStatus);
                });

    }
    private String calculateOverallOrderStatus(Order order) {
        String orderStatus = order.getStatus();
        String shippingStatus = String.valueOf(order.getShippingStatus());

        // Logic to compare order status and shipping status
        if (orderStatus.equals("Completed") && shippingStatus.equals("DELIVERED")) {
            return "Completed";
        } else if (orderStatus.equals("In Progress") && shippingStatus.equals("Shipped")) {
            return "In Progress";
        } else {
            return "Pending";
        }

    }

    public Mono<Order> createOrder(Order order){
        return orderRepository.save(order);
}

    public Flux<OrderedItems>getOrderedItems(){
        return orderedItemsRepository.findAll();
    }
    }





















/*
}
    public Mono <Order> retrieveOrderById(String orderId){
        return orderRepository.findById(orderId);
    }
    public Flux<Order> retrieveAll(){
        return orderRepository.findAll();
    }
    *//*public Mono<Order> updateOrder(String orderId,Order updatedOrder){
        return orderRepository.findById(orderId)
                .flatMap(existingOrder ->{
                    existingOrder.setCustomerId(updatedOrder.getCustomerId());
                    existingOrder.setProductid(updatedOrder.getProductid());
                    existingOrder.setPaid(updatedOrder.isPaid());
                    return orderRepository.save(existingOrder);
                        });

    }*//*
    public Mono<Void> deleteOrder(String orderId){
        return orderRepository.deleteById(orderId);
    }

}*/


