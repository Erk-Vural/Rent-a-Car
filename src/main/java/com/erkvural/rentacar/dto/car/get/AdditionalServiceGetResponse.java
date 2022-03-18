package com.erkvural.rentacar.dto.car.get;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AdditionalServiceGetResponse {

    private String name;

    private double dailyPrice;
}
