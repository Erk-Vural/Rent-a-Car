package com.erkvural.rentacar.dto.car.get;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarGetResponse {

    private double dailyPrice;

    private int modelYear;

    private String description;

    private long mileage;

    private String colorName;

    private String brandName;
}
