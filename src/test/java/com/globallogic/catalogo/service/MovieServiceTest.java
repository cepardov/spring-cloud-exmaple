package com.globallogic.catalogo.service;

import com.globallogic.catalogo.configuration.ExceptionMsgConfiguration;
import com.globallogic.catalogo.configuration.MessagesConfiguration;
import com.globallogic.catalogo.dto.MessageDto;
import com.globallogic.catalogo.dto.MovieDto;
import com.globallogic.catalogo.exception.RepositoryException;
import com.globallogic.catalogo.repository.MovieRepository;
import com.globallogic.catalogo.service.impl.MovieServiceImpl;
import com.globallogic.catalogo.utils.FakeData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieServiceImpl service;

    @Mock
    private MovieRepository repository;

    @Mock
    private ModelMapper mapper;

    @Mock
    private ExceptionMsgConfiguration exceptionMsg;

    @Mock
    private MessagesConfiguration messages;

    @Test
    void findAll() {
        //given
        when(mapper.map(any(), any())).thenReturn(FakeData.movieDto());
        when(repository.findAll()).thenReturn(FakeData.movieList());

        //when
        List<MovieDto> movieDtoList = service.findAll();

        //then
        assertEquals(FakeData.movieDtoList(), movieDtoList);
    }

    @Test
    void findByUuid() {
        when(mapper.map(any(), any())).thenReturn(FakeData.movieDto());
        when(repository.findByUuid(any())).thenReturn(Optional.of(FakeData.movie()));
        MovieDto movieDto = service.findByUuid("uuid");
        assertEquals(FakeData.movieDto(),movieDto);
    }

    @Test
    void findByUuid_ex() {
        when(exceptionMsg.getErrorMovieUuidNotFound()).thenReturn(FakeData.exceptionMsgConfiguration().getErrorMovieUuidNotFound());
        when(repository.findByUuid(any())).thenReturn(Optional.empty());
        RepositoryException e = assertThrows(RepositoryException.class, () -> service.findByUuid("uuid"));
        assertEquals(FakeData.exceptionMsgConfiguration().getErrorMovieUuidNotFound().getMessage(), e.getMessage());
    }

    @Test
    void save() {
        when(mapper.map(any(), any())).thenReturn(FakeData.movie()).thenReturn(FakeData.movieDto());
        when(repository.save(any())).thenReturn(FakeData.movie());
        MovieDto movieDto = service.save(FakeData.movieDto());
        assertEquals(FakeData.movieDto(), movieDto);
    }

    @Test
    void update() {
        when(mapper.map(any(), any())).thenReturn(FakeData.movieDto());
        when(repository.findByUuid(any())).thenReturn(Optional.of(FakeData.movie()));

        when(repository.save(any())).thenReturn(FakeData.movie());
        MovieDto movieDto = service.update(FakeData.movieDto());
        assertEquals(FakeData.movieDto(), movieDto);
    }

    @Test
    void update_ex() {
        when(exceptionMsg.getErrorUpdateMovie()).thenReturn(FakeData.exceptionMsgConfiguration().getErrorUpdateMovie());
        when(repository.findByUuid(any())).thenReturn(Optional.empty());

        RepositoryException e = assertThrows(RepositoryException.class, () -> service.update(FakeData.movieDto()));
        assertEquals(FakeData.exceptionMsgConfiguration().getErrorMovieUuidNotFound().getMessage(), e.getMessage());
    }

    @Test
    void deleteByUuid() {
        when(repository.findByUuid(any())).thenReturn(Optional.of(FakeData.movie()));

        when(messages.getMovieDeleted()).thenReturn(FakeData.messageDto());
        MessageDto messageDto = service.deleteByUuid("uuid");
        assertEquals(FakeData.messageDto().getMessage(), messageDto.getMessage());
    }

    @Test
    void deleteByUuid_ex() {
        when(exceptionMsg.getErrorDeleteMovieUuidNotFound()).thenReturn(FakeData.exceptionMsgConfiguration().getErrorDeleteMovieUuidNotFound());
        when(repository.findByUuid(any())).thenReturn(Optional.empty());

        RepositoryException e = assertThrows(RepositoryException.class, () -> service.deleteByUuid("uuid"));
        assertEquals(FakeData.exceptionMsgConfiguration().getErrorDeleteMovieUuidNotFound().getMessage(), e.getMessage());
    }
}