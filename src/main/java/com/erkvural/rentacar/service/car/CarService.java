package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarCreateDto;
import com.erkvural.rentacar.dto.car.delete.CarDeleteDto;
import com.erkvural.rentacar.dto.car.get.CarGetDto;
import com.erkvural.rentacar.dto.car.update.CarUpdateDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CarService {
    Result add(CarCreateDto carCreateDto);

    DataResult<List<CarGetDto>> getAll();

    DataResult<CarGetDto> getById(long id);

    DataResult<List<CarGetDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<CarGetDto>> getAllSorted(Sort.Direction direction);

    DataResult<List<CarGetDto>> getAllByDailyPriceLessThanEqual(double dailyPrice);

    Result update(CarUpdateDto carUpdateDto);

    Result delete(CarDeleteDto carDeleteDto);
}
