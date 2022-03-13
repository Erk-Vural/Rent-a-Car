package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.BrandCreateDto;
import com.erkvural.rentacar.dto.car.get.BrandGetDto;
import com.erkvural.rentacar.dto.car.update.BrandUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    Result add(BrandCreateDto brandCreateDto);

    DataResult<List<BrandGetDto>> getAll();

    DataResult<BrandGetDto> getById(long id);

    Result update(long id, BrandUpdateDto brandUpdateDto);

    Result delete(long brandDeleteDto);
}
