package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.Car;
import com.erkvural.rentacar.entity.car.CarMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarMaintenanceRepository extends JpaRepository<CarMaintenance, Long> {

    CarMaintenance findById(long id);

    List<CarMaintenance> findByCar_Id(long carId);

    List<CarMaintenance> findByCar(Car car);
}
