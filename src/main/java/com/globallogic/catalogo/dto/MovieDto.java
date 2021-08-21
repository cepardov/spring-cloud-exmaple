package com.globallogic.catalogo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "MovieDto", description = "Clase transporte objeto movie.")
public class MovieDto {

    @ApiModelProperty(hidden = true)
    private String uuid;

    @NotNull(message = "titulo no puede estar vacío")
    @ApiModelProperty(value = "Nombre de la pelicula", example = "Matrix", required = true)
    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "Descripcion de la pelicula", example = "Descripcion Matrix")
    private String description;

    @NotNull(message = "Fecha de lenzamiento no puede estar vacío")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "Fecha de lanzamiento", example = "2021-01-01", required = true)
    private LocalDate releaseDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "Valoracion de la pelicula", example = "8.5")
    private double valoration;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "URL imagen portada de la pelicula", example = "http://local/image.jpg")
    private String urlImagePoster;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "URL video trailer de la pelicula", example = "http://local/video.mp4")
    private String urlVideotrailer;

    @ApiModelProperty(hidden = true)
    private LocalDate createdOn;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(hidden = true)
    private LocalDate lastUpdated;
}
