package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.dto.car.create.CarMaintenanceCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarMaintenanceGetResponse;
import com.erkvural.rentacar.dto.car.update.CarMaintenanceUpdateRequest;
import com.erkvural.rentacar.service.car.CarMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car/maintenances")
public class CarMaintenanceController {
    private final CarMaintenanceService service;

    @Autowired
    public CarMaintenanceController(CarMaintenanceService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CarMaintenanceCreateRequest createRequest) throws BusinessException {
        return this.service.add(createRequest);
    }

    @GetMapping("/get/all")
    public DataResult<List<CarMaintenanceGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<CarMaintenanceGetResponse> get(@PathVariable long id) throws BusinessException {
        return service.getById(id);
    }

    @GetMapping("/get/carId={carId}")
    public SuccessDataResult<List<CarMaintenanceGetResponse>> getByCarId(@PathVariable long carId) {
        return service.getByCarId(carId);
    }

    @GetMapping("get/all/return-date/sort-direction={direction}")
    public DataResult<List<CarMaintenanceGetResponse>> getAllSorted(@PathVariable Sort.Direction direction) {
        return this.service.getAllSorted(direction);
    }

    @GetMapping("/get/all/page-no={pageNo}+page-size={pageSize}")
    public DataResult<List<CarMaintenanceGetResponse>> getAllPaged(@PathVariable int pageNo,
                                                                   @PathVariable int pageSize) {
        return this.service.getAllPaged(pageNo, pageSize);
    }

    @PutMapping("/update/id={id}")
    public Result update(@PathVariable long id, @RequestBody CarMaintenanceUpdateRequest updateRequest) throws BusinessException {
        return this.service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/id={id}")
    public Result delete(@PathVariable long id) throws BusinessException {
        return this.service.delete(id);
    }
}
