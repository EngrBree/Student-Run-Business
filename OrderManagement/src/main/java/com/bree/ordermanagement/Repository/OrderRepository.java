package com.bree.ordermanagement.Repository;

import com.bree.ordermanagement.Entity.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<Order,String> {
}
