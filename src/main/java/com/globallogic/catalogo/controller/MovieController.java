package com.globallogic.catalogo.controller;

import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.exception.RepositoryException;
import com.globallogic.catalogo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/v1/movie")
@RestController
public class MovieController {

    final MovieService movieService;

    @GetMapping("/{uuid}")
    public ResponseEntity<MovieDto> findByUuid(@PathVariable("uuid") String uuid) throws RepositoryException {
        return ResponseEntity.ok(movieService.findByUuid(uuid));
    }

    @PostMapping
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.save(movieDto));
    }

    @PatchMapping
    public ResponseEntity<MovieDto> patch(@RequestBody MovieDto movieDto) throws RepositoryException {
        return ResponseEntity.ok(movieService.update(movieDto));
    }
}
