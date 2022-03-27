package com.erkvural.rentacar.entity.customer;

import com.erkvural.rentacar.core.entity.User;
import com.erkvural.rentacar.entity.car.CarRental;
import com.erkvural.rentacar.entity.car.CardInfo;
import com.erkvural.rentacar.entity.car.Invoice;
import com.erkvural.rentacar.entity.car.Payment;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "CUSTOMERS")
public class Customer extends User {

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<CarRental> carRentals;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Invoice> invoices;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<CardInfo> cardInfos;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Payment> payments;
}
