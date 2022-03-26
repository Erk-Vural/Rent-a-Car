package com.erkvural.rentacar.entity.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "card_info_id")
    private CardInfo cardInfo;

    @ManyToOne
    @JoinColumn(name = "car_rental_id", nullable = false)
    private CarRental carRental;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Invoice invoice;
}
