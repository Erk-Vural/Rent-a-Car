package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarMaintenanceCreateDto;
import com.erkvural.rentacar.dto.car.create.CarRentalCreateDto;
import com.erkvural.rentacar.dto.car.get.CarMaintenanceGetDto;
import com.erkvural.rentacar.dto.car.get.CarRentalGetDto;
import com.erkvural.rentacar.dto.car.update.CarMaintenanceUpdateDto;
import com.erkvural.rentacar.dto.car.update.CarRentalUpdateDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRentalServiceImpl implements CarRentalService {

    @Override
    public Result add(CarRentalCreateDto carRentalCreateDto) {
        return null;
    }

    @Override
    public DataResult<List<CarRentalGetDto>> getAll() {
        return null;
    }

    @Override
    public DataResult<CarRentalGetDto> getById(long id) {
        return null;
    }

    @Override
    public DataResult<CarRentalGetDto> getByCarId(long carId) {
        return null;
    }

    @Override
    public DataResult<CarRentalGetDto> getByCustomerId(long customerId) {
        return null;
    }

    @Override
    public DataResult<List<CarRentalGetDto>> getAllStartDateSorted(Sort.Direction direction) {
        return null;
    }

    @Override
    public DataResult<List<CarRentalGetDto>> getAllEndDateSorted(Sort.Direction direction) {
        return null;
    }

    @Override
    public Result update(long id, CarRentalUpdateDto carRentalUpdateDto) {
        return null;
    }

    @Override
    public Result delete(long carRentalDeleteDto) {
        return null;
    }
}
