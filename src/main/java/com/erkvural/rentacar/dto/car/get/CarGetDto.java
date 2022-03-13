package com.erkvural.rentacar.dto.car.get;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarGetDto {

    private double dailyPrice;

    private int modelYear;

    private String description;

    private String colorName;

    private String brandName;
}
