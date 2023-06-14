package com.bree.ordermanagement.Client;

import com.bree.ordermanagement.Entity.Shipping;
import reactor.core.publisher.Mono;

public interface ShipmentManageClient {
   Mono<Shipping> updateStatus(String shipmentId) ;

}
