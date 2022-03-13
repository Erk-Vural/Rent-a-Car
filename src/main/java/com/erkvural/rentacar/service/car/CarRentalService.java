package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarRentalCreateDto;
import com.erkvural.rentacar.dto.car.get.CarRentalGetDto;
import com.erkvural.rentacar.dto.car.update.CarRentalUpdateDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarRentalService {
    Result add(CarRentalCreateDto carRentalCreateDto);

    DataResult<List<CarRentalGetDto>> getAll();

    DataResult<CarRentalGetDto> getById(long id);

    DataResult<CarRentalGetDto> getByCarId(long carId);

    DataResult<CarRentalGetDto> getByCustomerId(long customerId);

    DataResult<List<CarRentalGetDto>> getAllStartDateSorted(Sort.Direction direction);

    DataResult<List<CarRentalGetDto>> getAllEndDateSorted(Sort.Direction direction);

    Result update(long id, CarRentalUpdateDto carRentalUpdateDto);

    Result delete(long carRentalDeleteDto);

}
