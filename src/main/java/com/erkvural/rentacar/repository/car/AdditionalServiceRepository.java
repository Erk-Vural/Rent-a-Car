package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long> {

    Set<AdditionalService> findById(long id);

    Object findByName(String name);
}
