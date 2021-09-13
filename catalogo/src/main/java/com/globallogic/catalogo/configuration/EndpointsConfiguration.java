package com.globallogic.catalogo.configuration;

import com.globallogic.catalogo.dto.TheMovieDbConfDto;
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
@ConfigurationProperties("endpoints")
public class EndpointsConfiguration {

    private TheMovieDbConfDto apiTheMovieDb;
}
