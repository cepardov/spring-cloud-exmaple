package com.globallogic.catalogo.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class RepositoryException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -4607986199110034184L;

    private final Throwable throwable;
    private final String code;
    private final String message;

    public RepositoryException(Throwable throwable, String code, String message) {
        super(throwable);
        this.throwable = throwable;
        this.code = code;
        this.message = message;
    }

    public RepositoryException(String code, String message) {
        this.throwable = null;
        this.code = code;
        this.message = message;
    }

}
