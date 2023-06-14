package com.bree.shippingmanagement.Repository;

import com.bree.shippingmanagement.Entity.Shipping;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ShippingRepository extends ReactiveCrudRepository<Shipping,String> {
}
