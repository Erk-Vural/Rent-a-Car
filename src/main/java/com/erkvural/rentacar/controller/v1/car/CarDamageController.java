package com.erkvural.rentacar.controller.v1.car;

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

    private final CarDamageService service;

    @Autowired
    public CarDamageController(CarDamageService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CarDamageCreateRequest createRequest) {
        return this.service.add(createRequest);
    }

    @GetMapping("/get/all")
    public DataResult<List<CarDamageGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<CarDamageGetResponse> get(@PathVariable long id) {
        return service.getById(id);
    }

    @PutMapping("/update/id={id}")
    public Result update(@PathVariable long id, @RequestBody CarDamageUpdateRequest updateRequest) {
        return this.service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/id={id}")
    public Result delete(@PathVariable long id) {
        return this.service.delete(id);
    }
}
