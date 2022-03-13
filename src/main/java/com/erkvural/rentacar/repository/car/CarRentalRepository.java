package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CarRentalRepository extends JpaRepository<CarRental, Long> {

    CarRental findById(long id);

    Set<CarRental> findByCar_Id(long carId);

    Set<CarRental> findByCustomer_UserId(long customerId);
}
