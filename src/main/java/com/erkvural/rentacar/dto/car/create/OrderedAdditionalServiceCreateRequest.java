package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OrderedAdditionalServiceCreateRequest {

    @Positive
    private int quantity;

    @NotNull
    @PositiveOrZero
    private long additionalServiceId;

    @NotNull
    @PositiveOrZero
    private long carRentalId;
}
