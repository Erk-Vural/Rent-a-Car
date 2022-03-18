package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarRentalCreateRequest {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    @PositiveOrZero
    private long startMileage;

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

    private Set<OrderedAdditionalServiceCreateRequest> orderedAdditionalServiceCreateDtos;
}
