package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findById(long id);

    City findByName(String name);
}
