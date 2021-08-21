package com.globallogic.catalogo.controller;

import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1/movie")
@RestController
public class MovieController {

    final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.save(movieDto));
    }
}
