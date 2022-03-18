package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.dto.car.create.CarRentalCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarRentalGetResponse;
import com.erkvural.rentacar.dto.car.update.CarRentalUpdateRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarRentalService {
    Result add(CarRentalCreateRequest createRequest) throws BusinessException;

    SuccessDataResult<List<CarRentalGetResponse>> getAll();

    SuccessDataResult<CarRentalGetResponse> getById(long id) throws BusinessException;

    SuccessDataResult<List<CarRentalGetResponse>> getByCarId(long carId);

    SuccessDataResult<List<CarRentalGetResponse>> getByCustomerId(long customerId);

    DataResult<List<CarRentalGetResponse>> getAllStartDateSorted(Sort.Direction direction);

    DataResult<List<CarRentalGetResponse>> getAllEndDateSorted(Sort.Direction direction);

    Result update(long id, CarRentalUpdateRequest updateRequest) throws BusinessException;

    Result delete(long id) throws BusinessException;

}
