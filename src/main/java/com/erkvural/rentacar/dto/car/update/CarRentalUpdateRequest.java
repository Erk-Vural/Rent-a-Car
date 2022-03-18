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

    private LocalDate startDate;

    private LocalDate endDate;

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
