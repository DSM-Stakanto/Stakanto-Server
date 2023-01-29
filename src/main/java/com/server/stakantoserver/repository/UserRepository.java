package com.server.stakantoserver.repository;

import com.server.stakantoserver.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
