package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.CarDamage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDamageRepository extends JpaRepository<CarDamage, Long> {
}
