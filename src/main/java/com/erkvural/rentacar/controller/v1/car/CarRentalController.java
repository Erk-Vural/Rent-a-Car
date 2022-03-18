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
    private final CarRentalService carRentalService;

    @Autowired
    public CarRentalController(CarRentalService carRentalService) {
        this.carRentalService = carRentalService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CarRentalCreateRequest createRequest) throws BusinessException {
        return this.carRentalService.add(createRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<CarRentalGetResponse>> getAll() {
        return carRentalService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CarRentalGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return carRentalService.getById(id);
    }

    @GetMapping("/getByCarId")
    public SuccessDataResult<List<CarRentalGetResponse>> getByCarId(@RequestParam("id") int id) {
        return carRentalService.getByCarId(id);
    }

    @GetMapping("/getByCustomerId")
    public SuccessDataResult<List<CarRentalGetResponse>> getByCustomerId(@RequestParam("id") int id) {
        return carRentalService.getByCustomerId(id);
    }

    @GetMapping("/getAllStartDateSorted")
    public DataResult<List<CarRentalGetResponse>> getAllStartDateSorted(@RequestParam("direction") Sort.Direction direction) {
        return this.carRentalService.getAllStartDateSorted(direction);
    }

    @GetMapping("/getAllEndDateSorted")
    public DataResult<List<CarRentalGetResponse>> getAllEndDateSorted(@RequestParam("direction") Sort.Direction direction) {
        return this.carRentalService.getAllEndDateSorted(direction);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody CarRentalUpdateRequest updateRequest) throws BusinessException {
        return this.carRentalService.update(id, updateRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.carRentalService.delete(id);
    }
}
