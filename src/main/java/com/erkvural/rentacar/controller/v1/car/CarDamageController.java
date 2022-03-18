package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarDamageCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarDamageGetResponse;
import com.erkvural.rentacar.dto.car.update.CarDamageUpdateRequest;
import com.erkvural.rentacar.service.car.CarDamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car/car-damages")
public class CarDamageController {

    private final CarDamageService carDamageService;

    @Autowired
    public CarDamageController(CarDamageService carDamageService) {
        this.carDamageService = carDamageService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CarDamageCreateRequest createRequest) throws BusinessException {
        return this.carDamageService.add(createRequest);
    }


    @GetMapping("/getAll")
    public DataResult<List<CarDamageGetResponse>> getAll() {
        return carDamageService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CarDamageGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return carDamageService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody CarDamageUpdateRequest updateRequest) throws BusinessException {
        return this.carDamageService.update(id, updateRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.carDamageService.delete(id);
    }
}
