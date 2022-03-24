package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AdditionalServiceUpdateRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private double dailyPrice;

}
