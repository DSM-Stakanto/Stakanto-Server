package com.server.stakantoserver.repository;

import com.server.stakantoserver.entity.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Long> {
}
