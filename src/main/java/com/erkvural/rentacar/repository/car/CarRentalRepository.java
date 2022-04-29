package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRentalRepository extends JpaRepository<CarRental, Long> {


    CarRental findById(long id);


    List<CarRental> findByCar_Id(long carId);


    List<CarRental> findByCustomer_UserId(long customerId);
}
