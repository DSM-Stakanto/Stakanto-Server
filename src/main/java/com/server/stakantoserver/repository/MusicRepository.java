package com.server.stakantoserver.repository;

import com.server.stakantoserver.entity.Music;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepository extends CrudRepository<Music, Long> {
}
