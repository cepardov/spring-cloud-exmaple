package com.globallogic.catalogo.controller;

import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.service.ImportMovieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/import")
@RestController
public class ImportMovieController {

    final ImportMovieService importMovieService;

    @ApiOperation("Operacion que permite obtener un listado de peliculas desde TMDB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista de películas",response = MovieDto.class)
    })
    @GetMapping("/movieByName")
    public ResponseEntity<List<MovieDto>> findByName(@RequestBody MovieDto movieDto) throws Exception {
        return ResponseEntity.ok(importMovieService.findMovieByName(movieDto));
    }
}
