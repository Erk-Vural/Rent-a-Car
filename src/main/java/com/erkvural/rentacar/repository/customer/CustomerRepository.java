package com.erkvural.rentacar.repository.customer;

import com.erkvural.rentacar.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUserId(long userId);
}
