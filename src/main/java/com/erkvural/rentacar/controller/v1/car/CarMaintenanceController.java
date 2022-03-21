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
    private final CarMaintenanceService carMaintenanceService;

    @Autowired
    public CarMaintenanceController(CarMaintenanceService carMaintenanceService) {
        this.carMaintenanceService = carMaintenanceService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CarMaintenanceCreateRequest createRequest) throws BusinessException {
        return this.carMaintenanceService.add(createRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<CarMaintenanceGetResponse>> getAll() {
        return carMaintenanceService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CarMaintenanceGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return carMaintenanceService.getById(id);
    }

    @GetMapping("/getByCarId")
    public SuccessDataResult<List<CarMaintenanceGetResponse>> getByCarId(@RequestParam("id") long id) {
        return carMaintenanceService.getByCarId(id);
    }

    @GetMapping("/getAllSorted")
    public DataResult<List<CarMaintenanceGetResponse>> getAllSorted(@RequestParam("direction") Sort.Direction direction) {
        return this.carMaintenanceService.getAllSorted(direction);
    }

    @GetMapping("/getAllPaged")
    public DataResult<List<CarMaintenanceGetResponse>> getAllPaged(@RequestParam("pageNo") int pageNo,
                                                                   @RequestParam("pageSize") int pageSize) {
        return this.carMaintenanceService.getAllPaged(pageNo, pageSize);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody CarMaintenanceUpdateRequest updateRequest) throws BusinessException {
        return this.carMaintenanceService.update(id, updateRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.carMaintenanceService.delete(id);
    }
}
