package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.BrandCreateRequest;
import com.erkvural.rentacar.dto.car.get.BrandGetResponse;
import com.erkvural.rentacar.dto.car.update.BrandUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    Result add(BrandCreateRequest brandCreateDto) throws BusinessException;

    DataResult<List<BrandGetResponse>> getAll();

    DataResult<BrandGetResponse> getById(long id) throws BusinessException;

    Result update(long id, BrandUpdateRequest brandUpdateDto) throws BusinessException;

    Result delete(long brandDeleteDto) throws BusinessException;
}
