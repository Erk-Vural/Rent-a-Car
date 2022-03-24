package com.erkvural.rentacar.dto.car.get;

import com.erkvural.rentacar.constant.CarStatus;
import lombok.*;

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
