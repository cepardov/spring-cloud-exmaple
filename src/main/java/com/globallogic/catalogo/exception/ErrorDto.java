package com.globallogic.catalogo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    @NotNull
    @NotEmpty
    private String code;

    @NotNull
    @NotEmpty
    private String message;
}
