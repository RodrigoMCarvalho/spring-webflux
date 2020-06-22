package com.apirest.webflux.endpoint;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
//@RequestMapping("/playlist")  //testando o acesso pelo PlaylistHandler
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public Flux<Playlist> getPlaylist() {
        return playlistService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Playlist> getPlaylistById(@PathVariable("id") String id) {
        return playlistService.findById(id);
    }

    @PostMapping
    public Mono<Playlist> save(@RequestBody Playlist playlist) {
        return playlistService.save(playlist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return playlistService.delete(id);
    }


}
