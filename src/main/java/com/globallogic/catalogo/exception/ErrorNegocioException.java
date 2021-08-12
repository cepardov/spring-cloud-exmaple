package com.globallogic.catalogo.exception;

public class ErrorNegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String codigo;

    public ErrorNegocioException(String codigo, String mensaje){
        super(mensaje);
        this.codigo = codigo;
    }

    public ErrorNegocioException(ErrorDto error) {
        super(error.getMensaje());
        this.codigo = error.getCodigo();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return getMessage();
    }
}
