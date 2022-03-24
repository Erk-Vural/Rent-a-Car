package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.CarMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarMaintenanceRepository extends JpaRepository<CarMaintenance, Long> {

    CarMaintenance findById(long id);

    List<CarMaintenance> findByCar_Id(long carId);

}
