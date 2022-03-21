package com.erkvural.rentacar.dto.car.create;

import com.erkvural.rentacar.entity.car.CarRental;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PaymentCreateRequest {

    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private double total;

    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String CardNumber;

    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String CardholderName;

    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String expiryDate;

    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String securityCode;

    @NotNull
    @PositiveOrZero
    private long carRental;
}
