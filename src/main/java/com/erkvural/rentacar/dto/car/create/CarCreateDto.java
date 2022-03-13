package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarCreateDto {

    @NotNull
    @Min(10)
    @Max(500)
    private double dailyPrice;

    @NotNull
    @Min(1980)
    @Max(2021)
    private int modelYear;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 64)
    private String description;

    @NotNull
    @PositiveOrZero
    private long colorId;

    @NotNull
    @PositiveOrZero
    private long brandId;
}
