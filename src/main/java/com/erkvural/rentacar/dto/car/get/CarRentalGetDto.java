package com.erkvural.rentacar.dto.car.get;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarRentalGetDto {

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private double bill;

    private String rentedCityName;

    private String returnedCityName;

    private long customerId;

    private long carId;
}
