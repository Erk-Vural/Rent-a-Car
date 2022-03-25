package com.erkvural.rentacar.repository.customer;

import com.erkvural.rentacar.entity.customer.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer, Long> {
    CorporateCustomer findByUserId(long userId);

    CorporateCustomer findByEmail(String email);

    CorporateCustomer findByTaxNumber(String taxNumber);
}
