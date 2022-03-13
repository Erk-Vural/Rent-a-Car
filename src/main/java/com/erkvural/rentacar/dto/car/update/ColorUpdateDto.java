package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ColorUpdateDto {
    @NotNull
    @Positive
    private long id;

    @NotNull
    @NotBlank
    private String name;
}
