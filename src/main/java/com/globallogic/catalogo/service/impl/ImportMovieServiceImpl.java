package com.globallogic.catalogo.service.impl;

import com.globallogic.catalogo.client.TheMovieDbClient;
import com.globallogic.catalogo.configuration.CatalogConfiguration;
import com.globallogic.catalogo.configuration.ExceptionMsgConfiguration;
import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.dto.TheMovieDbResponseDto;
import com.globallogic.catalogo.service.ImportMovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImportMovieServiceImpl implements ImportMovieService {


    private final ModelMapper mapper;
    private final TheMovieDbClient theMovieDbClient;
    private final CatalogConfiguration configuration;
    private final ExceptionMsgConfiguration exceptionMsg;

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
