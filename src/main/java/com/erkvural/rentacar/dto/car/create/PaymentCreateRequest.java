package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PaymentCreateRequest {

    @NotNull
    private CardInfoCreateRequest cardInfo;

    @NotNull
    @PositiveOrZero
    private long carRentalId;
}
