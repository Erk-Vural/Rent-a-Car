package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CityCreateRequest {

    @NotNull
    @Size(min = 2, max = 50)
    private String name;
}
