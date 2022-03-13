package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarMaintenanceCreateDto;
import com.erkvural.rentacar.dto.car.get.CarMaintenanceGetDto;
import com.erkvural.rentacar.dto.car.update.CarMaintenanceUpdateDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarMaintenanceServiceImpl implements CarMaintenanceService {
    @Override
    public Result add(CarMaintenanceCreateDto carMaintenanceCreateDto) {
        return null;
    }

    @Override
    public DataResult<List<CarMaintenanceGetDto>> getAll() {
        return null;
    }

    @Override
    public DataResult<CarMaintenanceGetDto> getById(long id) {
        return null;
    }

    @Override
    public DataResult<CarMaintenanceGetDto> getByCarId(long carId) {
        return null;
    }

    @Override
    public DataResult<List<CarMaintenanceGetDto>> getAllPaged(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public DataResult<List<CarMaintenanceGetDto>> getAllSorted(Sort.Direction direction) {
        return null;
    }

    @Override
    public Result update(long id, CarMaintenanceUpdateDto carMaintenanceUpdateDto) {
        return null;
    }

    @Override
    public Result delete(long carMaintenanceDeleteDto) {
        return null;
    }
}
