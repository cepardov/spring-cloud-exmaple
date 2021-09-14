package com.globallogic.catalogo.client.impl;

import com.globallogic.catalogo.client.TheMovieDbClient;
import com.globallogic.catalogo.configuration.EndpointsConfiguration;
import com.globallogic.catalogo.constants.Constants;
import com.globallogic.catalogo.dto.TheMovieDbResponseDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TheMovieDbClientImpl implements TheMovieDbClient {

    private final RestTemplate restTemplate;
    private final EndpointsConfiguration endpoints;

    private HttpHeaders getHeadersSeguridad() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(Constants.AUTHORIZATION,
                Constants.BEARER + endpoints.getApiTheMovieDb().getToken());
        return headers;
    }

    @Override
    @HystrixCommand(fallbackMethod = "searchMovieDefault")
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
    public Optional<TheMovieDbResponseDto> searchMovieDefault(String query, int page) {
        return Optional.empty();
    }
}
