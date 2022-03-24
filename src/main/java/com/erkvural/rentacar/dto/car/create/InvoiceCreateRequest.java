package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceCreateRequest {

    @NotNull
    @Positive
    private long paymentId;
}
