package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.AdditionalServiceCreateDto;
import com.erkvural.rentacar.dto.car.get.AdditionalServiceGetDto;
import com.erkvural.rentacar.dto.car.update.AdditionalServiceUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalServiceServiceImpl implements AdditionalServiceService{
    @Override
    public Result add(AdditionalServiceCreateDto additionalServiceCreateDto) {
        return null;
    }

    @Override
    public DataResult<List<AdditionalServiceGetDto>> getAll() {
        return null;
    }

    @Override
    public DataResult<AdditionalServiceGetDto> getById(long id) {
        return null;
    }

    @Override
    public Result update(long id, AdditionalServiceUpdateDto additionalServiceUpdateDto) {
        return null;
    }

    @Override
    public Result delete(long additionalServiceDeleteDto) {
        return null;
    }
}
