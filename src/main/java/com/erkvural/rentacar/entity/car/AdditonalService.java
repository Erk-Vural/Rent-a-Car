package com.erkvural.rentacar.entity.car;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ADDITIONAL_SERVICES")
public class AdditonalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "daily_price", nullable = false)
    private double dailyPrice;

    @OneToMany(mappedBy = "additionalService", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<OrderedAdditionalService> orderedAdditionalServices;
}
