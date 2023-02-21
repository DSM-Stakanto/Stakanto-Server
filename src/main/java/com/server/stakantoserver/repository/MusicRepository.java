package com.server.stakantoserver.repository;

import com.server.stakantoserver.entity.Music;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MusicRepository extends CrudRepository<Music, Long> {
    Optional<Music> findByName(String name);
}
