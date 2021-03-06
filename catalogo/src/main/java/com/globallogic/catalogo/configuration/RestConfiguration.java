package com.globallogic.catalogo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = (SimpleClientHttpRequestFactory) restTemplate
                .getRequestFactory();
        simpleClientHttpRequestFactory.setReadTimeout(15000);
        simpleClientHttpRequestFactory.setConnectTimeout(15000);
        restTemplate.setRequestFactory(simpleClientHttpRequestFactory);

        return new RestTemplate();
    }
}
