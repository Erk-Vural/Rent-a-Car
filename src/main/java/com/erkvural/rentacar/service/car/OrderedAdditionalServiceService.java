package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.dto.car.create.OrderedAdditionalServiceCreateDto;
import com.erkvural.rentacar.dto.car.get.OrderedAdditionalServiceGetDto;
import com.erkvural.rentacar.entity.car.OrderedAdditionalService;

import java.util.List;

public interface OrderedAdditionalServiceService {

    void add(List<OrderedAdditionalServiceCreateDto> orderedAdditionalServiceCreateDtoList, int carRentalId);

    List<OrderedAdditionalServiceGetDto> getByCarRentalId(int carRentalId);

    Double calculateBill(List<OrderedAdditionalService> orderedAdditionalServices);
}
