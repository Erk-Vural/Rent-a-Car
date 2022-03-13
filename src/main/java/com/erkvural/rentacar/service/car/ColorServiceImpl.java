package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.ColorCreateDto;
import com.erkvural.rentacar.dto.car.get.ColorGetDto;
import com.erkvural.rentacar.dto.car.update.ColorUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService{
    @Override
    public Result add(ColorCreateDto colorCreateDto) {
        return null;
    }

    @Override
    public DataResult<List<ColorGetDto>> getAll() {
        return null;
    }

    @Override
    public DataResult<ColorGetDto> getById(long id) {
        return null;
    }

    @Override
    public Result update(long id, ColorUpdateDto colorUpdateDto) {
        return null;
    }

    @Override
    public Result delete(long colorDeleteDto) {
        return null;
    }
}
