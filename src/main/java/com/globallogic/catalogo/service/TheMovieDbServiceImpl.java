package com.globallogic.catalogo.service;

import com.globallogic.catalogo.configuration.EndpointsConfiguration;
import com.globallogic.catalogo.constantes.Constantes;
import com.globallogic.catalogo.dto.TheMovieDbResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
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
    public Optional<TheMovieDbResponseDto> searchMovie(String query) {
        HttpEntity<?> request = new HttpEntity<>(getHeadersSeguridad());
        Map<String, String> params = new HashMap<>();
        params.put("query",query);
        params.put("language","es");
        params.put("page","1");
        ResponseEntity<TheMovieDbResponseDto> res = restTemplate.exchange(
                endpoints.getApiTheMovieDb().getSearchMovie(),
                HttpMethod.GET,
                request,
                TheMovieDbResponseDto.class,
                params
        );
        return Optional.ofNullable(res.getBody());
    }
}
