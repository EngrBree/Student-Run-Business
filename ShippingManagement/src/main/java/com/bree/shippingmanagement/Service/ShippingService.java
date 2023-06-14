package com.bree.shippingmanagement.Service;

import com.bree.shippingmanagement.Client.OrderManageClient;
import com.bree.shippingmanagement.Entity.Shipping;
import com.bree.shippingmanagement.Entity.ShippingStatus;
import com.bree.shippingmanagement.Repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ShippingService {
    private  final  ShippingRepository shippingRepository;
    private final OrderManageClient orderManageClient;

    public ShippingService(ShippingRepository shippingRepository, OrderManageClient orderManageClient) {
        this.shippingRepository = shippingRepository;
        this.orderManageClient = orderManageClient;
    }
    public Mono<Shipping> createShipping(Shipping shipping){
        return orderManageClient.getOrderById(shipping.getOrderId())
                .flatMap(order -> {
                    shipping.setShippingAddress(order.getShippingAddress());
                    shipping.setStatus(ShippingStatus.PENDING);
                    return shippingRepository.save(shipping);
                });

    }
    public Mono<Shipping>updateStatus(Shipping shipping,String shipmentId){
        return shippingRepository.findById(shipmentId)
                .flatMap(existingShipment->{
                    existingShipment.setStatus(shipping.getStatus());
                    existingShipment.setTrackingNumber(shipping.getTrackingNumber());
                    return shippingRepository.save(existingShipment);
                });
    }

public Mono<Shipping> retrieveShipment(String shipmentId){
        return shippingRepository.findById(shipmentId);
}
public Flux<Shipping>retrieveAllShipments(Shipping shipping){
        return shippingRepository.findAll();
}







}
