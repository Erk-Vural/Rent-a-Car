package com.erkvural.rentacar.dto.customer.get;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class IndividualCustomerGetDto {

    private String email;

    private String firstName;

    private String lastName;
}
