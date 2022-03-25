package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarRentalCreateRequest {

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    @NotNull
    @Future
    private LocalDate endDate;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 64)
    private String description;

    @NotNull
    @Positive
    private long rentedCityId;

    @NotNull
    @Positive
    private long returnedCityId;

    @NotNull
    @Positive
    private long customerId;

    @NotNull
    @Positive
    private long carId;

    private Set<OrderedAdditionalServiceCreateRequest> orderedAdditionalServiceCreateRequestSet;
}
