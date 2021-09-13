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
    void whenRequestForAllMovies_thenReturnList() {
        //given
        when(mapper.map(any(), any())).thenReturn(FakeData.movieDto());
        when(repository.findAll()).thenReturn(FakeData.movieList());

        //when
        List<MovieDto> movieDtoList = service.findAll();

        //then
        assertEquals(FakeData.movieDtoList(), movieDtoList);
    }

    @Test
    void whenRequestForExistingMovie_thenReturnMovieDetails() {
        when(mapper.map(any(), any())).thenReturn(FakeData.movieDto());
        when(repository.findByUuid(any())).thenReturn(Optional.of(FakeData.movie()));
        MovieDto movieDto = service.findByUuid("uuid");
        assertEquals(FakeData.movieDto(),movieDto);
    }

    @Test
    void whenRequestForExistingMovie_thenReturnErrorMessage() {
        when(exceptionMsg.getErrorMovieUuidNotFound()).thenReturn(FakeData.exceptionMsgConfiguration().getErrorMovieUuidNotFound());
        when(repository.findByUuid(any())).thenReturn(Optional.empty());
        RepositoryException e = assertThrows(RepositoryException.class, () -> service.findByUuid("uuid"));
        assertEquals(FakeData.exceptionMsgConfiguration().getErrorMovieUuidNotFound().getMessage(), e.getMessage());
    }

    @Test
    void whenRequestForSaveMovie_thenReturnMovieSaved() {
        when(mapper.map(any(), any())).thenReturn(FakeData.movie()).thenReturn(FakeData.movieDto());
        when(repository.save(any())).thenReturn(FakeData.movie());
        MovieDto movieDto = service.save(FakeData.movieDto());
        assertEquals(FakeData.movieDto(), movieDto);
    }

    @Test
    void whenRequestForPatchMovie_thenReturnMovieUpdated() {
        when(mapper.map(any(), any())).thenReturn(FakeData.movieDto());
        when(repository.findByUuid(any())).thenReturn(Optional.of(FakeData.movie()));

        when(repository.save(any())).thenReturn(FakeData.movie());
        MovieDto movieDto = service.update(FakeData.movieDto());
        assertEquals(FakeData.movieDto(), movieDto);
    }

    @Test
    void whenRequestForPatchMovie_thenReturnErrorMessage() {
        when(exceptionMsg.getErrorUpdateMovie()).thenReturn(FakeData.exceptionMsgConfiguration().getErrorUpdateMovie());
        when(repository.findByUuid(any())).thenReturn(Optional.empty());

        RepositoryException e = assertThrows(RepositoryException.class, () -> service.update(FakeData.movieDto()));
        assertEquals(FakeData.exceptionMsgConfiguration().getErrorMovieUuidNotFound().getMessage(), e.getMessage());
    }

    @Test
    void whenRequestForDeleteMovie_thenReturnDeleteMessage() {
        when(repository.findByUuid(any())).thenReturn(Optional.of(FakeData.movie()));

        when(messages.getMovieDeleted()).thenReturn(FakeData.messageDto());
        MessageDto messageDto = service.deleteByUuid("uuid");
        assertEquals(FakeData.messageDto().getMessage(), messageDto.getMessage());
    }

    @Test
    void whenRequestForDeleteMovie_thenReturnErrorMessage() {
        when(exceptionMsg.getErrorDeleteMovieUuidNotFound()).thenReturn(FakeData.exceptionMsgConfiguration().getErrorDeleteMovieUuidNotFound());
        when(repository.findByUuid(any())).thenReturn(Optional.empty());

        RepositoryException e = assertThrows(RepositoryException.class, () -> service.deleteByUuid("uuid"));
        assertEquals(FakeData.exceptionMsgConfiguration().getErrorDeleteMovieUuidNotFound().getMessage(), e.getMessage());
    }
}