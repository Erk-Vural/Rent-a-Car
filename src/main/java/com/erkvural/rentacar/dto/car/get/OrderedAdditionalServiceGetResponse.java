package com.erkvural.rentacar.dto.car.get;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OrderedAdditionalServiceGetResponse {

    private int quantity;

    private String additionalServiceName;

    private long carRentalId;
}
