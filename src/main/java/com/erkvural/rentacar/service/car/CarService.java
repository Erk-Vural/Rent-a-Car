package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarGetResponse;
import com.erkvural.rentacar.dto.car.update.CarUpdateRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    Result add(CarCreateRequest carCreateDto);

    DataResult<List<CarGetResponse>> getAll();

    DataResult<CarGetResponse> getById(long id) throws BusinessException;

    DataResult<List<CarGetResponse>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<CarGetResponse>> getAllSorted(Sort.Direction direction);

    DataResult<List<CarGetResponse>> getAllByDailyPriceLessThanEqual(double dailyPrice);

    Result update(long id, CarUpdateRequest carUpdateDto) throws BusinessException;

    Result delete(long id) throws BusinessException;
}
