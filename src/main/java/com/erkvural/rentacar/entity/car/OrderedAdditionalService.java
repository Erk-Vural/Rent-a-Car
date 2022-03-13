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

    @ManyToOne()
    @JoinColumn(name = "additional_service_id", nullable = false)
    private AdditonalService additionalService;

}
