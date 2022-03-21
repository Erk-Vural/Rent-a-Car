package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarRentalUpdateRequest {

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    @NotNull
    @Future
    private LocalDate endDate;

    @NotNull
    @PositiveOrZero
    private long startMileage;

    @NotNull
    @PositiveOrZero
    private long endMileage;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 64)
    private String description;

    @NotNull
    @PositiveOrZero
    private long rentedCityId;

    @NotNull
    @PositiveOrZero
    private long returnedCityId;

    @NotNull
    @PositiveOrZero
    private long customerId;

    @NotNull
    @PositiveOrZero
    private long carId;

}
