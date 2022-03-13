package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findById(long id);

    Set<Car> findByBrandId(long brandId);

    Set<Car> findByColorId(long colorId);

    Set<Car> findByDailyPriceLessThanEqual(double dailyPrice);
}
