package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarUpdateRequest {

    @NotNull
    @PositiveOrZero
    private double dailyPrice;

    @NotNull
    @Min(1980)
    @Max(2022)
    private int modelYear;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 64)
    private String description;

    @NotNull
    @PositiveOrZero
    private double mileage;

    @NotNull
    @PositiveOrZero
    private long colorId;

    @NotNull
    @PositiveOrZero
    private long brandId;
}
