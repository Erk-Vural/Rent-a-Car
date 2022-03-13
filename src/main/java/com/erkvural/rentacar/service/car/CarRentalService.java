package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarRentalCreateDto;
import com.erkvural.rentacar.dto.car.delete.CarRentalDeleteDto;
import com.erkvural.rentacar.dto.car.get.CarRentalGetDto;
import com.erkvural.rentacar.dto.car.update.CarRentalUpdateDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CarRentalService {
    Result add(CarRentalCreateDto carRentalCreateDto);

    DataResult<List<CarRentalGetDto>> getAll();

    DataResult<CarRentalGetDto> getById(long id);

    DataResult<CarRentalGetDto> getByCarId(long carId);

    DataResult<CarRentalGetDto> getByCustomerId(long customerId);

    DataResult<List<CarRentalGetDto>> getAllReturnDateSorted(Sort.Direction direction);

    DataResult<List<CarRentalGetDto>> getAllRentalDateSorted(Sort.Direction direction);

    Result update(CarRentalUpdateDto carRentalUpdateDto);

    Result delete(CarRentalDeleteDto carRentalDeleteDto);

}
