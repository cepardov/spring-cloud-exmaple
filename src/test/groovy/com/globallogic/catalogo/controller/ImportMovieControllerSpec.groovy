package com.globallogic.catalogo.controller

import com.globallogic.catalogo.dto.MovieDto
import com.globallogic.catalogo.service.ImportMovieService
import com.globallogic.catalogo.util.FakeData
import spock.lang.Specification

class ImportMovieControllerSpec extends Specification {

    private ImportMovieController controller
    private ImportMovieService importMovieService = Mock(ImportMovieService)

    def setup() {
        controller = new ImportMovieController(
                importMovieService
        )
    }

    def "test findByName"() {
        given:
        importMovieService.findMovieByName(_ as MovieDto) >> FakeData.moviesdto
        when:
        controller.findByName(FakeData.movieDto)
        then:
        notThrown()
    }
}
