package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarDamageCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarDamageGetResponse;
import com.erkvural.rentacar.dto.car.update.CarDamageUpdateRequest;

import java.util.List;

public interface CarDamageService {
    Result add(CarDamageCreateRequest createRequest);

    DataResult<List<CarDamageGetResponse>> getAll();

    DataResult<CarDamageGetResponse> getById(long id);

    Result update(long id, CarDamageUpdateRequest updateRequest);

    Result delete(long id);
}
