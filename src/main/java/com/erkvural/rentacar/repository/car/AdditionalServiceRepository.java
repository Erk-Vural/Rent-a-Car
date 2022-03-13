package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.AdditonalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AdditionalServiceRepository extends JpaRepository<AdditonalService, Long> {

    Set<AdditonalService> findById(long id);
}
