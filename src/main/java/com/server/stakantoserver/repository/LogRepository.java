package com.server.stakantoserver.repository;

import com.server.stakantoserver.entity.Log;
import com.server.stakantoserver.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogRepository extends CrudRepository<Log, Long> {
    List<Log> findByUserAndGenreOrderByCreatedAtDesc(User user, String genre);
}
