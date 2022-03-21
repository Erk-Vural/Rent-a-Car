package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CardInfoUpdateRequest {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 32)
    private String CardNumber;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String CardholderName;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 10)
    private String expiryDate;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    private String securityCode;
}
