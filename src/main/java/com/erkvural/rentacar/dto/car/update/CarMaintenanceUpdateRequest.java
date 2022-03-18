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

    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String description;

    private LocalDate returnDate;

    @NotNull
    @PositiveOrZero
    private long carId;

}
