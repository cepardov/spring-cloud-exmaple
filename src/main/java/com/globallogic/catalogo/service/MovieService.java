package com.globallogic.catalogo.service;

import com.globallogic.catalogo.dto.MovieDto;

public interface MovieService {

    MovieDto findByUuid(String uuid);
    MovieDto save(MovieDto movieDto);
    //MovieDto update(MovieDto movieDto);
}
