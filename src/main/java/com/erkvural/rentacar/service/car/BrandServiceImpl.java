package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.BrandCreateDto;
import com.erkvural.rentacar.dto.car.get.BrandGetDto;
import com.erkvural.rentacar.dto.car.update.BrandUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService{
    @Override
    public Result add(BrandCreateDto brandCreateDto) {
        return null;
    }

    @Override
    public DataResult<List<BrandGetDto>> getAll() {
        return null;
    }

    @Override
    public DataResult<BrandGetDto> getById(long id) {
        return null;
    }

    @Override
    public Result update(long id, BrandUpdateDto brandUpdateDto) {
        return null;
    }

    @Override
    public Result delete(long brandDeleteDto) {
        return null;
    }
}
