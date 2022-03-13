package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AdditionalServiceUpdateDto {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Min(10)
    @Max(500)
    private double dailyPrice;

}
