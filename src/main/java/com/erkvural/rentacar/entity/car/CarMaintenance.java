package com.erkvural.rentacar.entity.car;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "CAR_MAINTENANCES")
public class CarMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "returnDate", nullable = false)
    private LocalDate returnDate;

    @Column(name = "description", length = 64)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
}
