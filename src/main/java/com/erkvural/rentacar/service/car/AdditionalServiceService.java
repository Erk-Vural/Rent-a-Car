package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.AdditionalServiceCreateDto;
import com.erkvural.rentacar.dto.car.get.AdditionalServiceGetDto;
import com.erkvural.rentacar.dto.car.update.AdditionalServiceUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdditionalServiceService {
    Result add(AdditionalServiceCreateDto additionalServiceCreateDto) throws BusinessException;

    DataResult<List<AdditionalServiceGetDto>> getAll();

    DataResult<AdditionalServiceGetDto> getById(long id) throws BusinessException;

    Result update(long id, AdditionalServiceUpdateDto additionalServiceUpdateDto) throws BusinessException;

    Result delete(long additionalServiceDeleteDto) throws BusinessException;
}
