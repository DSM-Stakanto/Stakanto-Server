package com.server.stakantoserver.repository;

import com.server.stakantoserver.entity.Refresh;
import com.server.stakantoserver.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshRepository extends CrudRepository<Refresh, Long> {
    Optional<Refresh> findByUser(User user);
}
