package com.erkvural.rentacar.entity.car;

import com.erkvural.rentacar.entity.customer.Customer;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "CARD_INFOS")
public class CardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "card_number")
    private String CardNumber;

    @Column(name = "cardholder_name")
    private String CardholderName;

    @Column(name = "expiry_date")
    private String expiryDate;

    @Column(name = "security_code")
    private String securityCode;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
