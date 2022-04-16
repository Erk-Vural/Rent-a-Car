package com.erkvural.rentacar.dto.car.get;

import com.erkvural.rentacar.entity.car.CardInfo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PaymentGetResponse {

    private double total;

    private long cardInfoId;

    private long carRentalId;
}
