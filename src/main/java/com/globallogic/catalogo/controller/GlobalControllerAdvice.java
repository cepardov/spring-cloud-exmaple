package com.globallogic.catalogo.controller;

import com.globallogic.catalogo.configuration.ExceptionMsgConfiguration;
import com.globallogic.catalogo.dto.MessageDto;
import com.globallogic.catalogo.exception.RepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalControllerAdvice {

    final ExceptionMsgConfiguration exceptionMsg;

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<MessageDto> repositoryException(RepositoryException e) {
        log.error(e.getMessage(), e);
        MessageDto messageDto = MessageDto.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(messageDto,e.getHttpStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> entityViolations(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        MessageDto messageDto = MessageDto.builder()
                .code(exceptionMsg.getErrorMovieExist().getCode())
                .message(exceptionMsg.getErrorMovieExist().getMessage())
                .build();
        return new ResponseEntity<>(messageDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        List<ObjectError> errors = e.getAllErrors();
        MessageDto messageDto = MessageDto.builder()
                .message(errors.stream().findFirst().get().getDefaultMessage())
                .build();
        return new ResponseEntity<>(messageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDto> exception(Exception e) {
        log.error(e.getMessage(), e);
        MessageDto messageDto = MessageDto.builder()
                .message("Error de servicio")
                .build();
        return new ResponseEntity<>(messageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
