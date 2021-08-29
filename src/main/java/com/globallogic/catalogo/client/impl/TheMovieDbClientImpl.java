package com.globallogic.catalogo.client.impl;

import com.globallogic.catalogo.client.TheMovieDbClient;
import com.globallogic.catalogo.configuration.EndpointsConfiguration;
import com.globallogic.catalogo.constants.Constants;
import com.globallogic.catalogo.dto.TheMovieDbResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class TheMovieDbClientImpl implements TheMovieDbClient {

    final RestTemplate restTemplate;
    final EndpointsConfiguration endpoints;

    private HttpHeaders getHeadersSeguridad() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(Constants.AUTHORIZATION,
                Constants.BEARER + endpoints.getApiTheMovieDb().getToken());
        return headers;
    }

    @Override
    public Optional<TheMovieDbResponseDto> searchMovie(String query, int page) {
        HttpEntity<?> request = new HttpEntity<>(getHeadersSeguridad());
        String url = endpoints.getApiTheMovieDb().getSearchMovie()
                + "?language=es"
                + "&query="+query
                + "&page="+page;
        ResponseEntity<TheMovieDbResponseDto> res = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                TheMovieDbResponseDto.class
        );
        return Optional.ofNullable(res.getBody());
    }
}
