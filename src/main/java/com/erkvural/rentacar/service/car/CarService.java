package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarCreateDto;
import com.erkvural.rentacar.dto.car.get.CarGetDto;
import com.erkvural.rentacar.dto.car.update.CarUpdateDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    Result add(CarCreateDto carCreateDto);

    DataResult<List<CarGetDto>> getAll();

    DataResult<CarGetDto> getById(long id);

    DataResult<List<CarGetDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<CarGetDto>> getAllSorted(Sort.Direction direction);

    DataResult<List<CarGetDto>> getAllByDailyPriceLessThanEqual(double dailyPrice);

    Result update(long id, CarUpdateDto carUpdateDto);

    Result delete(long carDeleteDto);
}
