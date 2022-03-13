package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.OrderedAdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderedAdditionalServiceRepository extends JpaRepository<OrderedAdditionalService, Long> {

    Set<OrderedAdditionalService> findByCarRental_Id(long carRentalId);
}
