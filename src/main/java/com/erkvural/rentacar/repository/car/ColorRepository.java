package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    Color findById(long id);

    Color findByName(String name);

}
