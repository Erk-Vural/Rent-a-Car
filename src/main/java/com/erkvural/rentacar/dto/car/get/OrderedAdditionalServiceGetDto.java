package com.erkvural.rentacar.dto.car.get;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OrderedAdditionalServiceGetDto {

    private String additionalServiceName;

    private int quantity;

    private long carRentalId;
}
