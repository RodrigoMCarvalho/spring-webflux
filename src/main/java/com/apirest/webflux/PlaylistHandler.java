package com.apirest.webflux;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

//@Component  classe comentada para testar o Events Stream
public class PlaylistHandler {

    @Autowired
    PlaylistService playlistService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(playlistService.findAll(), Playlist.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Playlist> playlistMono = playlistService.findById(id);
        return playlistMono.flatMap(p -> ok().body(playlistMono, Playlist.class))
                           .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<Playlist> playlist = request.bodyToMono(Playlist.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(playlist.flatMap(playlistService::save), Playlist.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Playlist> playlistMono = playlistService.findById(id);
        return playlistMono.flatMap(p -> noContent().build(playlistService.delete(id)))
                           .switchIfEmpty(notFound().build());
    }
}
