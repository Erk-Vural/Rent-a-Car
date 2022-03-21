package com.erkvural.rentacar.dto.car.get;

import com.erkvural.rentacar.entity.car.CarRental;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PaymentGetResponse {

    private double total;

    private String CardNumber;

    private String CardholderName;

    private String expiryDate;

    private String securityCode;

    private long carRental;
}
