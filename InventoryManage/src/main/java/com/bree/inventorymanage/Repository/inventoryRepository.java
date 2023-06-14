package com.bree.inventorymanage.Repository;

import com.bree.inventorymanage.Entities.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface inventoryRepository extends ReactiveCrudRepository<Product,Integer> {

    Mono<Product> findById(Integer productid);
}
