package com.erkvural.rentacar.repository.customer;

import com.erkvural.rentacar.entity.customer.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Long> {
    IndividualCustomer findByUserId(long userId);

    IndividualCustomer findByEmail(String email);

    IndividualCustomer findByNationalId(String nationalId);
}
