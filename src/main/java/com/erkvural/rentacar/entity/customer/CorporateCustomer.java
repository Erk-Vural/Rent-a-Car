package com.erkvural.rentacar.entity.customer;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "CORPORATE_CUSTOMERS")
public class CorporateCustomer extends Customer {
    @Column(name = "company_name", length = 64, nullable = false)
    private String companyName;

    @Column(name = "tax_number", length = 10, nullable = false, unique = true)
    private String taxNumber;
}
