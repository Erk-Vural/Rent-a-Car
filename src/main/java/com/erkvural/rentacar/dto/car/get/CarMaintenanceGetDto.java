package com.erkvural.rentacar.dto.car.get;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarMaintenanceGetDto {

    private String description;

    private LocalDate returnDate;

    private long carId;
}
