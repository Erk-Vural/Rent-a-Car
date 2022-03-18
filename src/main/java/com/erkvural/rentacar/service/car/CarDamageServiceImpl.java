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
public class CarDamageServiceImpl implements CarDamageService {
    @Override
    public Result add(CarDamageCreateRequest createRequest) throws BusinessException {
        return null;
    }

    @Override
    public DataResult<List<CarDamageGetResponse>> getAll() {
        return null;
    }

    @Override
    public DataResult<CarDamageGetResponse> getById(long id) throws BusinessException {
        return null;
    }

    @Override
    public Result update(long id, CarDamageUpdateRequest updateRequest) throws BusinessException {
        return null;
    }

    @Override
    public Result delete(long id) throws BusinessException {
        return null;
    }
}
