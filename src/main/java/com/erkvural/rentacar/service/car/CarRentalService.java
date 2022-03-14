package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.dto.car.create.CarRentalCreateDto;
import com.erkvural.rentacar.dto.car.get.CarRentalGetDto;
import com.erkvural.rentacar.dto.car.update.CarRentalUpdateDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarRentalService {
    Result add(CarRentalCreateDto carRentalCreateDto) throws BusinessException;

    SuccessDataResult<List<CarRentalGetDto>> getAll();

    SuccessDataResult<CarRentalGetDto> getById(long id) throws BusinessException;

    SuccessDataResult<List<CarRentalGetDto>> getByCarId(long carId);

    SuccessDataResult<List<CarRentalGetDto>> getByCustomerId(long customerId);

    DataResult<List<CarRentalGetDto>> getAllStartDateSorted(Sort.Direction direction);

    DataResult<List<CarRentalGetDto>> getAllEndDateSorted(Sort.Direction direction);

    Result update(long id, CarRentalUpdateDto carRentalUpdateDto) throws BusinessException;

    Result delete(long id) throws BusinessException;

}
