package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.CarMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CarMaintenanceRepository extends JpaRepository<CarMaintenance, Long> {

    CarMaintenance findById(long id);

    Set<CarMaintenance> findByCar_Id(long carId);
}
