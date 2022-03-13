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
public interface CarMaintenanceService {
    Result add(CarMaintenanceCreateDto carMaintenanceCreateDto);

    DataResult<List<CarMaintenanceGetDto>> getAll();

    DataResult<CarMaintenanceGetDto> getById(long id);

    DataResult<CarMaintenanceGetDto> getByCarId(long carId);

    DataResult<List<CarMaintenanceGetDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<CarMaintenanceGetDto>> getAllSorted(Sort.Direction direction);

    Result update(long id, CarMaintenanceUpdateDto carMaintenanceUpdateDto);

    Result delete(long carMaintenanceDeleteDto);
}
