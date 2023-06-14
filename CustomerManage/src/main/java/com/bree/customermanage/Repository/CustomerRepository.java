package com.bree.customermanage.Repository;

import com.bree.customermanage.Model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer,String> {
    @Override
    Mono<Customer> findById(String customerId);
}
