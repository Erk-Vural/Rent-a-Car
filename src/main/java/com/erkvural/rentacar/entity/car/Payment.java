package com.erkvural.rentacar.entity.car;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "PAYMENTS")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total")
    private double total;

    @Column(name = "card_number")
    private String CardNumber;

    @Column(name = "cardholder_name")
    private String CardholderName;

    @Column(name = "expriy_date")
    private String expiryDate;

    @Column(name = "security_code")
    private String securityCode;

    @OneToOne
    @JoinColumn(name = "car_rental_id")
    private CarRental carRental;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Invoice invoice;
}
