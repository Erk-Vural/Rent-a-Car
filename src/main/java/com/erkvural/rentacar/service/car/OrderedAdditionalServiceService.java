package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.dto.car.create.OrderedAdditionalServiceCreateDto;
import com.erkvural.rentacar.entity.car.OrderedAdditionalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface OrderedAdditionalServiceService {

    void add(Set<OrderedAdditionalServiceCreateDto> orderedAdditionalServiceCreateDtos, long carRentalId) throws BusinessException;

    Set<OrderedAdditionalService> getByCarRentalId(long carRentalId);

    Double calculateBill(List<OrderedAdditionalService> orderedAdditionalServices);
}
