package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.dto.car.create.CarRentalCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarRentalGetResponse;
import com.erkvural.rentacar.dto.car.update.CarRentalUpdateRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CarRentalService {
    Result add(CarRentalCreateRequest createRequest);

    SuccessDataResult<List<CarRentalGetResponse>> getAll();

    SuccessDataResult<CarRentalGetResponse> getById(long id);

    SuccessDataResult<List<CarRentalGetResponse>> getByCarId(long carId);

    SuccessDataResult<List<CarRentalGetResponse>> getByCustomerId(long customerId);

    DataResult<List<CarRentalGetResponse>> getAllStartDateSorted(Sort.Direction direction);

    DataResult<List<CarRentalGetResponse>> getAllEndDateSorted(Sort.Direction direction);

    Result update(long id, CarRentalUpdateRequest updateRequest);

    Result delete(long id);

    double calRentedTotal(long id);

}
