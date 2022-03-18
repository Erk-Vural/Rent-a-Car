package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BrandUpdateRequest {

    @NotNull
    @NotBlank
    private String name;
}
