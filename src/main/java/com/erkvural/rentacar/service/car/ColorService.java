package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.ColorCreateRequest;
import com.erkvural.rentacar.dto.car.get.ColorGetResponse;
import com.erkvural.rentacar.dto.car.update.ColorUpdateRequest;

import java.util.List;

public interface ColorService {

    Result add(ColorCreateRequest createRequest) throws BusinessException;

    DataResult<List<ColorGetResponse>> getAll();

    DataResult<ColorGetResponse> getById(long id) throws BusinessException;

    Result update(long id, ColorUpdateRequest updateRequest) throws BusinessException;

    Result delete(long id) throws BusinessException;
}