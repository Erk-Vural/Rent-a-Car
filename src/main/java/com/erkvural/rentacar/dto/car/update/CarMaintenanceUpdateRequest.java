package com.erkvural.rentacar.dto.car.update;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarMaintenanceUpdateRequest {

    @NotNull
    @Future
    private LocalDate returnDate;

    @Size(min = 2, max = 64)
    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Positive
    private long carId;
}
