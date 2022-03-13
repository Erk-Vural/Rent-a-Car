package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.AdditionalServiceCreateDto;
import com.erkvural.rentacar.dto.car.delete.AdditionalServiceDeleteDto;
import com.erkvural.rentacar.dto.car.get.AdditionalServiceGetDto;
import com.erkvural.rentacar.dto.car.update.AdditionalServiceUpdateDto;

import java.util.List;

public interface AdditionalServiceService {
    Result add(AdditionalServiceCreateDto additionalServiceCreateDto);

    DataResult<List<AdditionalServiceGetDto>> getAll();

    DataResult<AdditionalServiceGetDto> getById(long id);

    Result update(AdditionalServiceUpdateDto additionalServiceUpdateDto);

    Result delete(AdditionalServiceDeleteDto additionalServiceDeleteDto);
}
