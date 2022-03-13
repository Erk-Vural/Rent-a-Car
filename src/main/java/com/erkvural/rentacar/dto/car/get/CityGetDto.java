package com.erkvural.rentacar.dto.car.get;

import com.erkvural.rentacar.entity.car.CarRental;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CityGetDto {
    private String name;

    private Set<CarRental> rentedCityCarRentals;

    private Set<CarRental> ReturnedCityCarRentals;
}

