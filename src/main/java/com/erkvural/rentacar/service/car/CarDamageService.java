package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarDamageCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarDamageGetResponse;
import com.erkvural.rentacar.dto.car.update.CarDamageUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarDamageService {
    Result add(CarDamageCreateRequest createRequest) throws BusinessException;

    DataResult<List<CarDamageGetResponse>> getAll();

    DataResult<CarDamageGetResponse> getById(long id) throws BusinessException;

    Result update(long id, CarDamageUpdateRequest updateRequest) throws BusinessException;

    Result delete(long id) throws BusinessException;
}
