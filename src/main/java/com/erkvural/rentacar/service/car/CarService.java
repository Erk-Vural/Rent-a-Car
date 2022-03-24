package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.CarStatus;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarGetResponse;
import com.erkvural.rentacar.dto.car.update.CarUpdateRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CarService {
    Result add(CarCreateRequest createRequest) throws BusinessException;

    DataResult<List<CarGetResponse>> getAll();

    DataResult<CarGetResponse> getById(long id) throws BusinessException;

    DataResult<List<CarGetResponse>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<CarGetResponse>> getAllSorted(Sort.Direction direction);

    DataResult<List<CarGetResponse>> getAllByDailyPriceLessThanEqual(double dailyPrice);

    Result update(long id, CarUpdateRequest updateRequest) throws BusinessException;

    Result delete(long id) throws BusinessException;

    void setCarStatus(CarStatus status, long carId) throws BusinessException;

    void setMileage(double endMileage, long carId) throws BusinessException;
}
