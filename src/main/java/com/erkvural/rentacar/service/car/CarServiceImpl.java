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
public class CarServiceImpl implements CarService{
    @Override
    public Result add(CarCreateDto carCreateDto) {
        return null;
    }

    @Override
    public DataResult<List<CarGetDto>> getAll() {
        return null;
    }

    @Override
    public DataResult<CarGetDto> getById(long id) {
        return null;
    }

    @Override
    public DataResult<List<CarGetDto>> getAllPaged(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public DataResult<List<CarGetDto>> getAllSorted(Sort.Direction direction) {
        return null;
    }

    @Override
    public DataResult<List<CarGetDto>> getAllByDailyPriceLessThanEqual(double dailyPrice) {
        return null;
    }

    @Override
    public Result update(long id, CarUpdateDto carUpdateDto) {
        return null;
    }

    @Override
    public Result delete(long carDeleteDto) {
        return null;
    }
}
