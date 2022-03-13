package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.dto.car.create.OrderedAdditionalServiceCreateDto;
import com.erkvural.rentacar.dto.car.get.OrderedAdditionalServiceGetDto;
import com.erkvural.rentacar.entity.car.OrderedAdditionalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedAdditionalServiceServiceImpl implements OrderedAdditionalServiceService{
    @Override
    public void add(List<OrderedAdditionalServiceCreateDto> orderedAdditionalServiceCreateDtoList, int carRentalId) {

    }

    @Override
    public List<OrderedAdditionalServiceGetDto> getByCarRentalId(int carRentalId) {
        return null;
    }

    @Override
    public Double calculateBill(List<OrderedAdditionalService> orderedAdditionalServices) {
        return null;
    }
}
