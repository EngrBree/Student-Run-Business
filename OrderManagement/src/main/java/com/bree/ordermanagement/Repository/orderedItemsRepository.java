package com.bree.ordermanagement.Repository;

import com.bree.ordermanagement.Entity.OrderedItems;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface orderedItemsRepository extends ReactiveCrudRepository<OrderedItems,Long> {
}
