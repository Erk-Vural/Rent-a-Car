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
    @Size(min = 16, max = 16)
    private String CardNumber;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 64)
    private String CardholderName;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 5)
    private String expiryDate;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 3)
    private String securityCode;
}
