package com.globallogic.catalogo.service;

import com.globallogic.catalogo.configuration.CatalogConfiguration;
import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.dto.TheMovieDbResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImportMovieServiceImpl implements ImportMovieService{

    final ModelMapper mapper;
    final TheMovieDbService theMovieDbService;
    final CatalogConfiguration configuration;

    @Override
    public List<MovieDto> findMovieByName(MovieDto movieDto) throws Exception {
        Optional<TheMovieDbResponseDto> response = theMovieDbService.searchMovie(movieDto.getTitle(), 1);

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
            throw new Exception("asd");
        }
    }
}
