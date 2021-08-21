package com.globallogic.catalogo.configuration;

import com.globallogic.catalogo.dto.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("errors")
public class ExceptionMsgConfiguration {

    private ErrorDto errorMovieExist;
    private ErrorDto errorUpdateMovie;
    private ErrorDto errorMovieUuidNotFound;
    private ErrorDto errorDeleteMovieUuidNotFound;
}
