package com.globallogic.catalogo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TheMovieDbMovieDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("poster_path")
    private String urlImagePoster;

    @JsonProperty("overview")
    private String description;

    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("release_date")
    private LocalDate releaseDate;

    @JsonProperty("popularity")
    private double valoration;

}
