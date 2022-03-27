package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findById(long id);
}
