package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.ColorCreateRequest;
import com.erkvural.rentacar.dto.car.get.ColorGetResponse;
import com.erkvural.rentacar.dto.car.update.ColorUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ColorService {

    Result add(ColorCreateRequest colorCreateDto) throws BusinessException;

    DataResult<List<ColorGetResponse>> getAll();

    DataResult<ColorGetResponse> getById(long id) throws BusinessException;

    Result update(long id, ColorUpdateRequest colorUpdateDto) throws BusinessException;

    Result delete(long colorDeleteDto) throws BusinessException;
}