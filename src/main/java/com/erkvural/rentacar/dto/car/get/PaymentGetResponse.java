package com.erkvural.rentacar.dto.car.get;

import com.erkvural.rentacar.entity.car.Payment;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PaymentGetResponse {

    private double total;

    private Payment payment;

    private long carRental;
}
