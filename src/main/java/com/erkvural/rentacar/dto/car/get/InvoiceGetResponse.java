package com.erkvural.rentacar.dto.car.get;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceGetResponse {

    private LocalDate createDate;

    private LocalDate rentStartDate;

    private LocalDate rentEndDate;

    private int totalRentDays;

    private double total;

    private long customerId;

    private long paymentId;
}
