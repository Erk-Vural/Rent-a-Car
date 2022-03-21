package com.erkvural.rentacar.dto.car.get;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CardInfoGetResponse {

    private String CardNumber;

    private String CardholderName;

    private String expiryDate;

    private String securityCode;
}
