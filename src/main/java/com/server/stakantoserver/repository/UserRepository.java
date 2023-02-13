package com.server.stakantoserver.repository;

import com.server.stakantoserver.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByAccountID(String accountId);
}
