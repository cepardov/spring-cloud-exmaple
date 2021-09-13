package com.globallogic.catalogo.client;

import com.globallogic.catalogo.dto.TheMovieDbResponseDto;

import java.util.Optional;

public interface TheMovieDbClient {

    Optional<TheMovieDbResponseDto> searchMovie(String query, int page);
}
