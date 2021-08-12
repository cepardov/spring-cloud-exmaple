package com.globallogic.catalogo.configuration;

import com.globallogic.catalogo.dto.TheMovieDbConfDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@ConfigurationProperties("endpoints")
public class EndpointsConfiguration {

    private TheMovieDbConfDto ApiTheMovieDb;
}
