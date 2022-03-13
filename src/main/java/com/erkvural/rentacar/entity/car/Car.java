package com.erkvural.rentacar.entity.car;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "CARS")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "daily_price", nullable = false)
    private double dailyPrice;

    @Column(name = "model_year", nullable = false)
    private int modelYear;

    @Column(name = "description", length = 64)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne()
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;
}
