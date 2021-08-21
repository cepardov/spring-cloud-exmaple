package com.globallogic.catalogo.controller;

import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.dto.MessageDto;
import com.globallogic.catalogo.exception.RepositoryException;
import com.globallogic.catalogo.service.MovieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/movie")
@RestController
public class MovieController {

    final MovieService movieService;

    @ApiOperation("Operacion que permite obtener todas las películas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista de películas",response = MovieDto.class)
    })
    @GetMapping
    public ResponseEntity<List<MovieDto>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @ApiOperation("Operacion que permite obtener una película")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pelicula",response = MovieDto.class),
            @ApiResponse(code = 404, message = "Mensaje de pelicula no encontrada", response = MessageDto.class)
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<MovieDto> findByUuid(@PathVariable("uuid") String uuid) throws RepositoryException {
        return ResponseEntity.ok(movieService.findByUuid(uuid));
    }

    @ApiOperation("Operacion que permite persistir película")
    @ApiResponses({
            @ApiResponse(code = 200, message = "La pelicula se ha guardado con éxito",response = MovieDto.class),
            @ApiResponse(code = 400, message = "La pelicula ya existe", response = MessageDto.class)
    })
    @PostMapping
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.save(movieDto));
    }

    @ApiOperation("Operacion que permite actualizar película")
    @ApiResponses({
            @ApiResponse(code = 200, message = "La pelicula se ha actualizado con éxito",response = MovieDto.class),
            @ApiResponse(code = 404, message = "La pelicula ya no existe", response = MessageDto.class)
    })
    @PatchMapping
    public ResponseEntity<MovieDto> patch(@RequestBody MovieDto movieDto) throws RepositoryException {
        return ResponseEntity.ok(movieService.update(movieDto));
    }

    @ApiOperation("Operacion que permite eliminar película")
    @ApiResponses({
            @ApiResponse(code = 200, message = "La pelicula se ha eliminado con éxito"),
            @ApiResponse(code = 400, message = "La pelicula ya no existe", response = MessageDto.class)
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<MessageDto> delete(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(movieService.deleteByUuid(uuid));
    }
}
