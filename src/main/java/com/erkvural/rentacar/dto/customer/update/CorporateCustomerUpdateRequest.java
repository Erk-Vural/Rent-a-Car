package com.erkvural.rentacar.dto.customer.update;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CorporateCustomerUpdateRequest {
    @NotNull
    @Positive
    private int userId;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 2, max = 64)
    private String companyName;

    @NotNull
    @Size(min = 2, max = 64)
    private String taxNumber;
}
