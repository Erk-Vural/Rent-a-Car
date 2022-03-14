package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CityCreateDto;
import com.erkvural.rentacar.dto.car.get.CityGetDto;
import com.erkvural.rentacar.dto.car.update.CityUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    Result add(CityCreateDto cityCreateDto) throws BusinessException;

    DataResult<List<CityGetDto>> getAll();

    DataResult<CityGetDto> getById(long id) throws BusinessException;

    Result update(long id, CityUpdateDto cityUpdateDto) throws BusinessException;

    Result delete(long cityDeleteDto) throws BusinessException;
}
