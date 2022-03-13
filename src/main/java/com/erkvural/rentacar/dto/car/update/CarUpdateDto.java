package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarUpdateDto {
    @NotNull
    @Positive
    private long id;

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
    private int colorId;

    @NotNull
    @PositiveOrZero
    private int brandId;
}
