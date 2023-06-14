package com.bree.ordermanagement.Client;

import com.bree.ordermanagement.Entity.Shipping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ShipmentManageImpl implements  ShipmentManageClient {


    private final WebClient webClient;

    public ShipmentManageImpl(@Value("${ShippingManagement.http://localhost:8081}") String shippingManagementUrl ) {
        this.webClient=WebClient.builder()
                .baseUrl(shippingManagementUrl)
                .build();
    }




    @Override
    public Mono<Shipping> updateStatus(String shipmentId) {
        return webClient.get()
                .uri("/shippments/{shipmentId}")
                .retrieve()
                .bodyToMono(Shipping.class);
    }


}
