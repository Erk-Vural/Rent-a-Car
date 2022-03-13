package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CityCreateDto;
import com.erkvural.rentacar.dto.car.get.CityGetDto;
import com.erkvural.rentacar.dto.car.update.CityUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements  CityService{
    @Override
    public Result add(CityCreateDto cityCreateDto) {
        return null;
    }

    @Override
    public DataResult<List<CityGetDto>> getAll() {
        return null;
    }

    @Override
    public DataResult<CityGetDto> getById(long id) {
        return null;
    }

    @Override
    public Result update(long id, CityUpdateDto cityUpdateDto) {
        return null;
    }

    @Override
    public Result delete(long cityDeleteDto) {
        return null;
    }
}
