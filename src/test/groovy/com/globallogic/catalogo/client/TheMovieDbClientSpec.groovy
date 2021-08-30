package com.globallogic.catalogo.client

import com.globallogic.catalogo.client.impl.TheMovieDbClientImpl
import com.globallogic.catalogo.configuration.EndpointsConfiguration
import com.globallogic.catalogo.util.FakeData
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class TheMovieDbClientSpec extends Specification {

    private TheMovieDbClient client
    private RestTemplate restTemplate = Mock()
    private EndpointsConfiguration endpoints = Mock()

    def setup() {
        client = new TheMovieDbClientImpl(
                restTemplate,
                endpoints
        )
    }

    def "test searchMovie"() {
        given:
        endpoints.getApiTheMovieDb() >> FakeData.endpointsConfiguration.getApiTheMovieDb()
        restTemplate.exchange(_, _, _, _) >> ResponseEntity.ok(FakeData.theMovieDbResponseDto)
        when:
        client.searchMovie("asd", 1)
        then:
        notThrown()
    }
}
