package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarDamageUpdateRequest {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 64)
    private String description;


    @NotNull
    @PositiveOrZero
    private long carId;
}
