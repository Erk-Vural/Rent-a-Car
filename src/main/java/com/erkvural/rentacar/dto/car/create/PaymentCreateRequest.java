package com.erkvural.rentacar.dto.car.create;

import com.erkvural.rentacar.entity.car.CardInfo;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PaymentCreateRequest {

    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private double total;

    @NotNull
    private CardInfoCreateRequest cardInfo;

    @NotNull
    @PositiveOrZero
    private long carRentalId;
}
