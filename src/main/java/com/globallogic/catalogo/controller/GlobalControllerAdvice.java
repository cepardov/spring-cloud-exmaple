package com.globallogic.catalogo.controller;

import com.globallogic.catalogo.configuration.ExceptionMsgConfiguration;
import com.globallogic.catalogo.exception.ErrorDto;
import com.globallogic.catalogo.exception.RepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> entityViolations(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        Map<String, Object> response = new HashMap<>();
        response.put("codigo", exceptionMsg.getErrorMovieExist().getCode());
        response.put("message", exceptionMsg.getErrorMovieExist().getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
