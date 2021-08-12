package com.globallogic.catalogo.service;

import com.globallogic.catalogo.dto.TheMovieDbMovieDto;
import com.globallogic.catalogo.configuration.EndpointsConfiguration;
import com.globallogic.catalogo.constantes.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TheMovieDbServiceImpl implements TheMovieDbService {

    RestTemplate restTemplate;
    EndpointsConfiguration endpoints;

    private HttpHeaders getHeadersSeguridad() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(Constantes.AUTHORIZATION,
                Constantes.BEARER + endpoints.getApiTheMovieDb().getToken());
        return headers;
    }

    @Override
    public Optional<TheMovieDbMovieDto> asd() {

        return Optional.empty();
    }
}
