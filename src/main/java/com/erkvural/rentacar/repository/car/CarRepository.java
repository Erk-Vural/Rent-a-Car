package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findById(long id);

    List<Car> findByBrandId(long brandId);

    List<Car> findByColorId(long colorId);

    List<Car> findByDailyPriceLessThanEqual(double dailyPrice);
}
