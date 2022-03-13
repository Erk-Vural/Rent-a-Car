package com.erkvural.rentacar.dto.customer.delete;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CorporateCustomerDeleteDto {
    @NotNull
    @Positive
    private int userId;
}
