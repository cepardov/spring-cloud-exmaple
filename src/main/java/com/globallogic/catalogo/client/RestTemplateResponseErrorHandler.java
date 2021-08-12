package com.globallogic.catalogo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.catalogo.constantes.Constantes;
import com.globallogic.catalogo.exception.ErrorDto;
import com.globallogic.catalogo.exception.ErrorNegocioException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if (response.getRawStatusCode() == Constantes.COD_ERROR_NEGOCIO) {
            return true;
        }
        return super.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getRawStatusCode() == Constantes.COD_ERROR_NEGOCIO) {
            byte[] bytes = StreamUtils.copyToByteArray(response.getBody());
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorDto errorDto = objectMapper.readValue(bytes, ErrorDto.class);
            throw new ErrorNegocioException(errorDto.getCodigo(), errorDto.getMensaje());
        }
        super.handleError(response);
    }
}
