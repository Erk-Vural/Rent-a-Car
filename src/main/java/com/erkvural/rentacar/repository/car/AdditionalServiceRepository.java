package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long> {

    List<AdditionalService> findById(long id);

    AdditionalService findByName(String name);
}
