package com.globallogic.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheMovieDbConfDto {

    private String token;
    private String searchMovie;
    private String urlImages;
}
