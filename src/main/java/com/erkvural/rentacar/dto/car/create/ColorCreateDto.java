package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ColorCreateDto {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 64)
    private String name;
}
