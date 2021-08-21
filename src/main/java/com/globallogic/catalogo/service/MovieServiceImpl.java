package com.globallogic.catalogo.service;

import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.entity.Movie;
import com.globallogic.catalogo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    final ModelMapper mapper;
    final MovieRepository repository;

    @Override
    public MovieDto findByUuid(String uuid) throws Exception {
        return mapper.map(
                repository.findByUuid(uuid).orElseThrow(() -> new Exception("no encontrado")),
                MovieDto.class
        );
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        return mapper.map(
                repository.save(mapper.map(movieDto, Movie.class)),
                MovieDto.class);
    }

    @Override
    public MovieDto update(MovieDto movieDto) throws Exception {
        Movie movie = mapper.map(
                repository.findByUuid(movieDto.getUuid()).orElseThrow(() -> new Exception("no encontrado")),
                Movie.class);
        return mapper.map(
                repository.save(movie),
                MovieDto.class
        );
    }
}
