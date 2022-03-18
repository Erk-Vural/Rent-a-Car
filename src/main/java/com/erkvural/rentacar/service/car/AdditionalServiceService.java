package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.AdditionalServiceCreateRequest;
import com.erkvural.rentacar.dto.car.get.AdditionalServiceGetResponse;
import com.erkvural.rentacar.dto.car.update.AdditionalServiceUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdditionalServiceService {
    Result add(AdditionalServiceCreateRequest additionalServiceCreateDto) throws BusinessException;

    DataResult<List<AdditionalServiceGetResponse>> getAll();

    DataResult<AdditionalServiceGetResponse> getById(long id) throws BusinessException;

    Result update(long id, AdditionalServiceUpdateRequest additionalServiceUpdateDto) throws BusinessException;

    Result delete(long additionalServiceDeleteDto) throws BusinessException;
}
