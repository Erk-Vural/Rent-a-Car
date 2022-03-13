package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.BrandCreateDto;
import com.erkvural.rentacar.dto.car.delete.BrandDeleteDto;
import com.erkvural.rentacar.dto.car.get.BrandGetDto;
import com.erkvural.rentacar.dto.car.update.BrandUpdateDto;

import java.util.List;

public interface BrandService {
    Result add(BrandCreateDto brandCreateDto);

    DataResult<List<BrandGetDto>> getAll();

    DataResult<BrandGetDto> getById(long id);

    Result update(BrandUpdateDto brandUpdateDto);

    Result delete(BrandDeleteDto brandDeleteDto);
}
