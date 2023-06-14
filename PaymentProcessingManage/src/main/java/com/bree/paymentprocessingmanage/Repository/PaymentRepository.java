package com.bree.paymentprocessingmanage.Repository;

import com.bree.paymentprocessingmanage.Entity.Pay;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PaymentRepository extends ReactiveCrudRepository<Pay, String> {

}
