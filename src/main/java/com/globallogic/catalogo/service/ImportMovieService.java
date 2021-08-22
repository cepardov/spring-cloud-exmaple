package com.globallogic.catalogo.service;

import com.globallogic.catalogo.dto.MovieDto;

import java.util.List;

public interface ImportMovieService {

    List<MovieDto> findMovieByName(MovieDto movieDto) throws Exception;
}
