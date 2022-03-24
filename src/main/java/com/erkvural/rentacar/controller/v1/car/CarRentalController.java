package com.erkvural.rentacar.controller.v1.car;


import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.dto.car.create.CarRentalCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarRentalGetResponse;
import com.erkvural.rentacar.dto.car.update.CarRentalUpdateRequest;
import com.erkvural.rentacar.service.car.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car/rentals")
public class CarRentalController {
    private final CarRentalService service;

    @Autowired
    public CarRentalController(CarRentalService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CarRentalCreateRequest createRequest) throws BusinessException {
        return this.service.add(createRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<CarRentalGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<CarRentalGetResponse> get(@PathVariable long id) throws BusinessException {
        return service.getById(id);
    }

    @GetMapping("/getByCarId/carId={carId}")
    public SuccessDataResult<List<CarRentalGetResponse>> getByCarId(@PathVariable long carId) {
        return service.getByCarId(carId);
    }

    @GetMapping("/getByCustomerId/customerId={customerId}")
    public SuccessDataResult<List<CarRentalGetResponse>> getByCustomerId(@PathVariable long customerId) {
        return service.getByCustomerId(customerId);
    }

    @GetMapping("/getAllStartDateSorted/direction={direction}")
    public DataResult<List<CarRentalGetResponse>> getAllStartDateSorted(@PathVariable Sort.Direction direction) {
        return this.service.getAllStartDateSorted(direction);
    }

    @GetMapping("/getAllEndDateSorted/direction={direction}")
    public DataResult<List<CarRentalGetResponse>> getAllEndDateSorted(@PathVariable Sort.Direction direction) {
        return this.service.getAllEndDateSorted(direction);
    }

    @PutMapping("/update/id={id}")
    public Result update(@PathVariable long id, @RequestBody CarRentalUpdateRequest updateRequest) throws BusinessException {
        return this.service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/id={id}")
    public Result delete(@PathVariable long id) throws BusinessException {
        return this.service.delete(id);
    }
}
