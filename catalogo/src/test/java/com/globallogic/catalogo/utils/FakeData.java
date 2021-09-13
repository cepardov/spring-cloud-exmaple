package com.globallogic.catalogo.utils;

import com.globallogic.catalogo.configuration.ExceptionMsgConfiguration;
import com.globallogic.catalogo.configuration.MessagesConfiguration;
import com.globallogic.catalogo.dto.MessageDto;
import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.entity.Movie;
import com.google.common.io.Files;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeData {

    private static final ModelMapper mapper = new ModelMapper();

    public static Movie movie() {
        return Movie.builder()
                .id(1L)
                .uuid("uuid")
                .title("title")
                .description("desc")
                .urlImagePoster("http://")
                .urlVideotrailer("http://")
                .createdOn(LocalDate.now())
                .build();
    }

    public static List<Movie> movieList() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(FakeData.movie());
        return movieList;
    }

    public static MovieDto movieDto() {
        return mapper.map(FakeData.movie(), MovieDto.class);
    }

    public static List<MovieDto> movieDtoList() {
        List<MovieDto> movieDtoList = new ArrayList<>();
        movieDtoList.add(mapper.map(FakeData.movie(), MovieDto.class));
        return movieDtoList;
    }

    public static MessageDto messageDto() {
        return MessageDto.builder()
                .code("0")
                .message("asd")
                .build();
    }

    public static ExceptionMsgConfiguration exceptionMsgConfiguration() {
        return ExceptionMsgConfiguration.builder()
                .errorMovieUuidNotFound(FakeData.messageDto())
                .errorUpdateMovie(FakeData.messageDto())
                .errorDeleteMovieUuidNotFound(FakeData.messageDto())
                .build();
    }

    public static MessagesConfiguration messagesConfiguration() {
        return MessagesConfiguration.builder()
                .movieDeleted(FakeData.messageDto())
                .build();
    }
}
