package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.BrandCreateDto;
import com.erkvural.rentacar.dto.car.get.BrandGetDto;
import com.erkvural.rentacar.dto.car.update.BrandUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    Result add(BrandCreateDto brandCreateDto) throws BusinessException;

    DataResult<List<BrandGetDto>> getAll();

    DataResult<BrandGetDto> getById(long id) throws BusinessException;

    Result update(long id, BrandUpdateDto brandUpdateDto) throws BusinessException;

    Result delete(long brandDeleteDto) throws BusinessException;
}
