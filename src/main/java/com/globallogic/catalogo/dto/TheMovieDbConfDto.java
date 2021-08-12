package com.globallogic.catalogo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TheMovieDbConfDto {

    private String token;
    private String searchMovie;
}
