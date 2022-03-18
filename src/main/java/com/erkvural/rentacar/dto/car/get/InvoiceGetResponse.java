package com.erkvural.rentacar.dto.car.get;

import com.erkvural.rentacar.entity.car.CarRental;
import com.erkvural.rentacar.entity.customer.Customer;
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

    private double bill;

    private long carRentalId;

    private long customerId;
}
