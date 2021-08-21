package com.globallogic.catalogo.service;

import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.exception.RepositoryException;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll();
    MovieDto findByUuid(String uuid) throws RepositoryException;
    MovieDto save(MovieDto movieDto);
    MovieDto update(MovieDto movieDto) throws RepositoryException;
    void deleteByUuid(String uuid) throws RepositoryException;
}
