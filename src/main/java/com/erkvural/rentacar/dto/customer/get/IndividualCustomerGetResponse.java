package com.erkvural.rentacar.dto.customer.get;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class IndividualCustomerGetResponse {

    private String email;

    private String firstName;

    private String lastName;
}
