package com.globallogic.catalogo.service

import com.globallogic.catalogo.client.TheMovieDbClient
import com.globallogic.catalogo.configuration.CatalogConfiguration
import com.globallogic.catalogo.configuration.ExceptionMsgConfiguration
import com.globallogic.catalogo.service.impl.ImportMovieServiceImpl
import com.globallogic.catalogo.util.FakeData
import org.modelmapper.ModelMapper
import spock.lang.Specification
import spock.lang.Unroll

class ImportMovieServiceSpec extends Specification {

    private ImportMovieService service
    private ModelMapper mapper = new ModelMapper()
    private TheMovieDbClient theMovieDbClient = Mock(TheMovieDbClient)
    private CatalogConfiguration configuration = Mock(CatalogConfiguration)
    private ExceptionMsgConfiguration exceptionMsg = Mock(ExceptionMsgConfiguration)

    def setup() {
        service = new ImportMovieServiceImpl(
                mapper,
                theMovieDbClient,
                configuration,
                exceptionMsg
        )
    }

    @Unroll
    def "test findMovieByName"() {
        given:
        exceptionMsg.getErrorTmdbMovieNotFound() >> FakeData.exceptionMsgConfiguration.errorTmdbMovieNotFound
        configuration.getTheMovieDb() >> FakeData.catalogConfiguration.theMovieDb
        4 * theMovieDbClient.searchMovie(_ as String, 1) >>> [
                FakeData.theMovieDbResponseOptional,
                FakeData.theMovieDbResponseOptionalNoImage,
                FakeData.theMovieDbResponseOptionalEmptyImage,
                FakeData.theMovieDbResponseOptionalNotFound
        ]
        when: "Find first movie in TMDB complete info"
        service.findMovieByName(FakeData.movieDto)
        then: "Not thrown"
        notThrown()

        when: "Find second movie in TMDB no image"
        service.findMovieByName(FakeData.movieDto)
        then: "Not thrown"
        notThrown()

        when: "Find third movie in TMDB empty image"
        service.findMovieByName(FakeData.movieDto)
        then: "Not thrown"
        notThrown()

        when: "Find fourth movie in TMDB not found"
        service.findMovieByName(FakeData.movieDto)
        then: "thrown exception"
        thrown(Exception)
    }
}
