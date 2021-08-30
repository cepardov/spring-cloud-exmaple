package com.globallogic.catalogo.service.impl;

import com.globallogic.catalogo.client.TheMovieDbClient;
import com.globallogic.catalogo.configuration.CatalogConfiguration;
import com.globallogic.catalogo.configuration.ExceptionMsgConfiguration;
import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.dto.TheMovieDbResponseDto;
import com.globallogic.catalogo.service.ImportMovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImportMovieServiceImpl implements ImportMovieService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TheMovieDbClient theMovieDbClient;

    @Autowired
    private CatalogConfiguration configuration;

    @Autowired
    private ExceptionMsgConfiguration exceptionMsg;

    @Override
    public List<MovieDto> findMovieByName(MovieDto movieDto) throws Exception {
        Optional<TheMovieDbResponseDto> response = theMovieDbClient.searchMovie(movieDto.getTitle(), 1);

        if (response.isPresent()) {
            return response.get().getMovies().stream()
                    .map(theMovieDbMovieDto -> {
                        String urlImg = theMovieDbMovieDto.getUrlImagePoster();
                        if (null != urlImg && !urlImg.isEmpty()) {
                            theMovieDbMovieDto.setUrlImagePoster(
                                    configuration.getTheMovieDb().getUrlImages().concat(urlImg)
                            );
                        }
                        return theMovieDbMovieDto;
                    })
                    .map(theMovieDbMovieDto -> mapper.map(theMovieDbMovieDto, MovieDto.class))
                    .collect(Collectors.toList());
        } else {
            throw new Exception(exceptionMsg.getErrorTmdbMovieNotFound().getMessage());
        }
    }
}
