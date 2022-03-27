package com.erkvural.rentacar.dto.car.update;

import com.erkvural.rentacar.dto.car.create.PaymentCreateRequest;
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
    private double endMileage;

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

    private boolean rememberMe;

    private PaymentCreateRequest paymentCreateRequest;
}
