package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CityCreateRequest;
import com.erkvural.rentacar.dto.car.get.CityGetResponse;
import com.erkvural.rentacar.dto.car.update.CityUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    Result add(CityCreateRequest createRequest) throws BusinessException;

    DataResult<List<CityGetResponse>> getAll();

    DataResult<CityGetResponse> getById(long id) throws BusinessException;

    Result update(long id, CityUpdateRequest updateRequest) throws BusinessException;

    Result delete(long id) throws BusinessException;
}
