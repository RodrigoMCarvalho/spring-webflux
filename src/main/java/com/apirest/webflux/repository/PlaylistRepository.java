package com.apirest.webflux.repository;

import com.apirest.webflux.document.Playlist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String> {

}
