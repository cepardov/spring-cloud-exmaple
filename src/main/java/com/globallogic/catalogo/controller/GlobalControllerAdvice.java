package com.globallogic.catalogo.controller;

import com.globallogic.catalogo.configuration.ExceptionMsgConfiguration;
import com.globallogic.catalogo.dto.ErrorDto;
import com.globallogic.catalogo.exception.RepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalControllerAdvice {

    final ExceptionMsgConfiguration exceptionMsg;

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<ErrorDto> repositoryException(RepositoryException e) {
        log.error(e.getMessage(), e);
        ErrorDto errorDto = ErrorDto.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(errorDto,e.getHttpStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> entityViolations(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        ErrorDto errorDto = ErrorDto.builder()
                .code(exceptionMsg.getErrorMovieExist().getCode())
                .message(exceptionMsg.getErrorMovieExist().getMessage())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }
}
