package com.akoca.producterbasketballapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String position;
    @NotBlank
    private String teamName;
}
