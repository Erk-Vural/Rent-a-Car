package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceCreateRequest {

    @NotNull
    @PositiveOrZero
    private long customerId;

    @NotNull
    @PositiveOrZero
    private long paymentId;
}
