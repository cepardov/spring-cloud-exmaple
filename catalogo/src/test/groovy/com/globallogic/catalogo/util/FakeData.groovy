package com.globallogic.catalogo.util

import com.globallogic.catalogo.configuration.CatalogConfiguration
import com.globallogic.catalogo.configuration.EndpointsConfiguration
import com.globallogic.catalogo.configuration.ExceptionMsgConfiguration
import com.globallogic.catalogo.dto.MessageDto
import com.globallogic.catalogo.dto.MovieDto
import com.globallogic.catalogo.dto.TheMovieDbConfDto
import com.globallogic.catalogo.dto.TheMovieDbMovieDto
import com.globallogic.catalogo.dto.TheMovieDbResponseDto
import com.globallogic.catalogo.entity.Movie
import org.modelmapper.ModelMapper

import java.time.LocalDate

class FakeData {

    private static final ModelMapper mapper = new ModelMapper()

    static theMovieDbConfDto = TheMovieDbConfDto.builder()
            .token("dasDEWDEW")
            .searchMovie("http://url")
            .urlImages("http://images")
            .build()

    static endpointsConfiguration = EndpointsConfiguration.builder()
            .apiTheMovieDb(theMovieDbConfDto)
            .build()

    static messageDto = MessageDto.builder()
            .code("00")
            .message("message")
            .build()

    static exceptionMsgConfiguration = ExceptionMsgConfiguration.builder()
            .errorTmdbMovieNotFound(messageDto)
            .build()

    static catalogConfiguration = CatalogConfiguration.builder()
            .theMovieDb(theMovieDbConfDto)
            .build()

    static theMovieDbMovieDtoEmptyImage = TheMovieDbMovieDto.builder()
            .id(1)
            .title("title")
            .urlImagePoster("")
            .description("description")
            .releaseDate(LocalDate.now())
            .valoration(8.0)
            .build()

    static theMovieDbMovieDtoNoImage = TheMovieDbMovieDto.builder()
            .id(1)
            .title("title")
            .description("description")
            .releaseDate(LocalDate.now())
            .valoration(8.0)
            .build()

    static theMovieDbMovieDto = TheMovieDbMovieDto.builder()
            .id(1)
            .title("title")
            .urlImagePoster("http://")
            .description("description")
            .releaseDate(LocalDate.now())
            .valoration(7.9)
            .build()

    static theMovieDbResponseDtoEmptyImage = TheMovieDbResponseDto.builder()
            .page(1)
            .totalPages(1)
            .movies([theMovieDbMovieDtoEmptyImage])
            .build()

    static theMovieDbResponseDtoNoImage = TheMovieDbResponseDto.builder()
            .page(1)
            .totalPages(1)
            .movies([theMovieDbMovieDtoNoImage])
            .build()

    static theMovieDbResponseDto = TheMovieDbResponseDto.builder()
            .page(1)
            .totalPages(1)
            .movies([theMovieDbMovieDto])
            .build()

    static theMovieDbResponseOptionalEmptyImage = Optional.of(theMovieDbResponseDtoEmptyImage)

    static theMovieDbResponseOptionalNoImage = Optional.of(theMovieDbResponseDtoNoImage)

    static theMovieDbResponseOptional = Optional.of(theMovieDbResponseDto)

    static theMovieDbResponseOptionalNotFound = Optional.empty()

    static movie = Movie.builder()
            .id(1L)
            .uuid("uuid")
            .title("title")
            .description("desc")
            .urlImagePoster("http://")
            .urlVideotrailer("http://")
            .createdOn(LocalDate.now())
            .build()

    static movieDto = mapper.map(movie, MovieDto.class)

    static movies = [movie]

    static moviesdto = [mapper.map(movie, MovieDto.class)]
}
