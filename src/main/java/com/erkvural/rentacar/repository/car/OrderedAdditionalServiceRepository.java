package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.OrderedAdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderedAdditionalServiceRepository extends JpaRepository<OrderedAdditionalService, Long> {

    Set<OrderedAdditionalService> findByCarRental_Id(long carRentalId);
}
