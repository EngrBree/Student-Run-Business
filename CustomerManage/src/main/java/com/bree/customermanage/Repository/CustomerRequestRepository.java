package com.bree.customermanage.Repository;

import com.bree.customermanage.Model.CustomerRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRequestRepository extends ReactiveCrudRepository<CustomerRequest,String> {

}
