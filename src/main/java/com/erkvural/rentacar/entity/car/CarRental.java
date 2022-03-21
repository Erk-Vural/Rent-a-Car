package com.erkvural.rentacar.entity.car;

import com.erkvural.rentacar.entity.customer.Customer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "CAR_RENTAL")
public class CarRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "start_mileage")
    private double startMileage;

    @Column(name = "end_mileage")
    private double endMileage;

    @Column(name = "description", length = 64)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "rented_city_id", nullable = false)
    private City rentedCity;

    @ManyToOne()
    @JoinColumn(name = "returned_city_id", nullable = false)
    private City returnedCity;

    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @OneToMany(mappedBy = "carRental", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<OrderedAdditionalService> orderedAdditionalServices;

    @OneToOne(mappedBy = "carRental", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Payment payment;
}
