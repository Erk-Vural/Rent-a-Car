package com.erkvural.rentacar.dto.car.create;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarMaintenanceCreateRequest {

    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String description;

    private LocalDate returnDate;

    @NotNull
    @PositiveOrZero
    private long carId;
}
