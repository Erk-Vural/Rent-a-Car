package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findById(long id);

    Brand findByName(String name);

    boolean existsByName(String name);
}
