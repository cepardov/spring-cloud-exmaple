package com.globallogic.catalogo.service;

import com.globallogic.catalogo.dto.TheMovieDbResponseDto;

import java.util.Optional;

public interface TheMovieDbService {

    Optional<TheMovieDbResponseDto> searchMovie(String query, int page);
}
