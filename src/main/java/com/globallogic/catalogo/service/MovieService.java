package com.globallogic.catalogo.service;

import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.exception.RepositoryException;

public interface MovieService {

    MovieDto findByUuid(String uuid) throws RepositoryException;
    MovieDto save(MovieDto movieDto);
    MovieDto update(MovieDto movieDto) throws RepositoryException;
}
