package com.globallogic.catalogo.service;

import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.entity.Movie;
import com.globallogic.catalogo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Movie movie = repository.findByUuid(movieDto.getUuid()).orElseThrow(() -> new Exception("no encontrado"));
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setValoration(movieDto.getValoration());
        movie.setUrlImagePoster(movieDto.getUrlImagePoster());
        movie.setUrlVideotrailer(movieDto.getUrlVideotrailer());
        return mapper.map(
                repository.save(movie),
                MovieDto.class
        );
    }
}
