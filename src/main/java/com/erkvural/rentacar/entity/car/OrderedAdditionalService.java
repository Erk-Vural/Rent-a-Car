package com.erkvural.rentacar.entity.car;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ORDERED_ADDITIONAL_SERVICES")
public class OrderedAdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "bill")
    private double bill;

    @ManyToOne()
    @JoinColumn(name = "additional_service_id", nullable = false)
    private AdditionalService additionalService;

    @ManyToOne()
    @JoinColumn(name = "car_rental_id", nullable = false)
    private CarRental carRental;

}
