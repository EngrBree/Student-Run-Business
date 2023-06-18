package com.bree.usermanage.Repository;

import com.bree.usermanage.Model.appUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends ReactiveCrudRepository<appUser,Long> {
    Optional<appUser>findByEmail(String Email);
}
