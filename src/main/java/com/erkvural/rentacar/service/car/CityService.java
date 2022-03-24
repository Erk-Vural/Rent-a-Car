package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CityCreateRequest;
import com.erkvural.rentacar.dto.car.get.CityGetResponse;
import com.erkvural.rentacar.dto.car.update.CityUpdateRequest;

import java.util.List;

public interface CityService {
    Result add(CityCreateRequest createRequest);

    DataResult<List<CityGetResponse>> getAll();

    DataResult<CityGetResponse> getById(long id);

    Result update(long id, CityUpdateRequest updateRequest);

    Result delete(long id);
}
