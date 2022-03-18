package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.dto.car.create.CarMaintenanceCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarMaintenanceGetResponse;
import com.erkvural.rentacar.dto.car.update.CarMaintenanceUpdateRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarMaintenanceService {
    Result add(CarMaintenanceCreateRequest createRequest) throws BusinessException;

    DataResult<List<CarMaintenanceGetResponse>> getAll();

    DataResult<CarMaintenanceGetResponse> getById(long id) throws BusinessException;

    SuccessDataResult<List<CarMaintenanceGetResponse>> getByCarId(long carId);

    DataResult<List<CarMaintenanceGetResponse>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<CarMaintenanceGetResponse>> getAllSorted(Sort.Direction direction);

    Result update(long id, CarMaintenanceUpdateRequest updateRequest) throws BusinessException;

    Result delete(long id) throws BusinessException;
}
