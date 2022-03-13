package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AdditionalServiceCreateDto {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Min(10)
    @Max(500)
    private double dailyPrice;
}
