package com.bree.shippingmanagement.Controller;

import com.bree.shippingmanagement.Entity.Shipping;
import com.bree.shippingmanagement.Service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/shippments")
public class ShippingController {
    private final ShippingService shippingService;
    @Autowired
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }
 @PostMapping
 @ResponseStatus(HttpStatus.CREATED)
    public Mono<Shipping> createNewShipment(@RequestBody Shipping shipping){
        return shippingService.createShipping(shipping);
 }
 @GetMapping("/{shipmentId}")
    public Mono<Shipping> findShipmentById(@PathVariable String shipmentId){
        return shippingService.retrieveShipment(shipmentId);
 }
 @PutMapping("/{shipmentId}")
    public Mono<Shipping>updateStatus(@RequestBody Shipping shipping,@PathVariable String shipmentId){
        return shippingService.updateStatus(shipping,shipmentId);
 }
    @GetMapping
      public Flux<Shipping> findAllShipments(Shipping shipping){
        return shippingService.retrieveAllShipments(shipping);
    }
}
