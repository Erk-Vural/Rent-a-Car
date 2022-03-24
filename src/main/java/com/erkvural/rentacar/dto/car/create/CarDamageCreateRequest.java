package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarDamageCreateRequest {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 64)
    private String description;


    @NotNull
    @Positive
    private long carId;
}
