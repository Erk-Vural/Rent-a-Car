package com.erkvural.rentacar.dto.customer.get;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CorporateCustomerGetDto {

    private String email;

    private String companyName;

    private String taxNumber;
}
