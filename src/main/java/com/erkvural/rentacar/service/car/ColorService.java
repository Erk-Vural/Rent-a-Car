package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.ColorCreateDto;
import com.erkvural.rentacar.dto.car.get.ColorGetDto;
import com.erkvural.rentacar.dto.car.update.ColorUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ColorService {

    Result add(ColorCreateDto colorCreateDto) throws BusinessException;

    DataResult<List<ColorGetDto>> getAll();

    DataResult<ColorGetDto> getById(long id) throws BusinessException;

    Result update(long id, ColorUpdateDto colorUpdateDto) throws BusinessException;

    Result delete(long colorDeleteDto) throws BusinessException;
}