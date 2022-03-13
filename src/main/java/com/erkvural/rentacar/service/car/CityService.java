package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CityCreateDto;
import com.erkvural.rentacar.dto.car.delete.CityDeleteDto;
import com.erkvural.rentacar.dto.car.get.CityGetDto;
import com.erkvural.rentacar.dto.car.update.CityUpdateDto;

import java.util.List;

public interface CityService {
    Result add(CityCreateDto cityCreateDto);

    DataResult<List<CityGetDto>> getAll();

    DataResult<CityGetDto> getById(long id);

    Result update(CityUpdateDto cityUpdateDto);

    Result delete(CityDeleteDto cityDeleteDto);
}
