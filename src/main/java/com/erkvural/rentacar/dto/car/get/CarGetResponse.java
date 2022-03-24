package com.erkvural.rentacar.dto.car.get;

import com.erkvural.rentacar.core.enums.CarStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarGetResponse {

    private CarStatus status;

    private double dailyPrice;

    private int modelYear;

    private String description;

    private double mileage;

    private String colorName;

    private String brandName;
}
