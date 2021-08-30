package com.globallogic.catalogo.controller

import com.globallogic.catalogo.dto.MovieDto
import com.globallogic.catalogo.service.MovieService
import com.globallogic.catalogo.util.FakeData
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class MovieControllerSpec extends Specification {

    private MovieController movieController
    private MovieService movieService = Mock(MovieService)

    def setup() {
        movieController = new MovieController(
                movieService
        )
    }

    def "FindAll"() {
        given:
        movieService.findAll() >> FakeData.moviesdto
        when:
        def res = movieController.findAll()
        then: "not causes exceptions"
        notThrown()
        and: "statusCodes equals 200 OK"
        res.statusCode == HttpStatus.OK
        and: "List of result content 1 element"
        res.body.size() == 1
        and: "response is instance of ResponseEntity"
        res instanceof ResponseEntity<List<MovieDto>>
    }

    def "FindByUuid"() {
        given:
        movieService.findByUuid(_ as String) >> FakeData.movieDto
        when:
        def res =movieController.findByUuid("uuid")
        then: "not causes exceptions"
        notThrown()
        and: "statusCodes equals 200 OK"
        res.statusCode == HttpStatus.OK
        and: "response is instance of ResponseEntity"
        res instanceof ResponseEntity<MovieDto>
    }

    def "SaveMovie"() {
        given:
        movieService.save(_ as MovieDto) >> FakeData.movieDto
        when:
        def res = movieController.saveMovie(FakeData.movieDto)
        then: "On save not thrown"
        notThrown()
        and: "statusCodes equals 200 OK"
        res.statusCode == HttpStatus.OK
        and: "response is instance of ResponseEntity"
        res instanceof ResponseEntity<MovieDto>
    }

    def "Patch"() {
        given:
        movieService.update(_ as MovieDto) >> FakeData.movieDto
        when:
        def res = movieController.patch(FakeData.movieDto)
        then: "On patch not thrown"
        notThrown()
        and: "statusCodes equals 200 OK"
        res.statusCode == HttpStatus.OK
        and: "response is instance of ResponseEntity"
        res instanceof ResponseEntity<MovieDto>
    }

    def "Delete"() {
        given:
        movieService.deleteByUuid(_ as String) >> FakeData.messageDto
        when:
        movieController.delete("uuid")
        then:
        notThrown()
    }
}
