package com.globallogic.catalogo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    @NotNull
    @NotEmpty
    private String message;
}
