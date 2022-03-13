package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarMaintenanceCreateDto;
import com.erkvural.rentacar.dto.car.get.CarMaintenanceGetDto;
import com.erkvural.rentacar.dto.car.update.CarMaintenanceUpdateDto;
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
    public Result add(@RequestBody CarMaintenanceCreateDto carMaintenanceCreateDto) {
        return this.carMaintenanceService.add(carMaintenanceCreateDto);
    }

    @GetMapping("/getAll")
    public DataResult<List<CarMaintenanceGetDto>> getAll() {
        return carMaintenanceService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CarMaintenanceGetDto> get(@RequestParam("id") long id) {
        return carMaintenanceService.getById(id);
    }

    @GetMapping("/getByCarId")
    public DataResult<CarMaintenanceGetDto> getByCarId(@RequestParam("id") int id) {
        return carMaintenanceService.getByCarId(id);
    }

    @GetMapping("/getAllSorted")
    public DataResult<List<CarMaintenanceGetDto>> getAllSorted(@RequestParam("direction") Sort.Direction direction) {
        return this.carMaintenanceService.getAllSorted(direction);
    }

    @GetMapping("/getAllPaged")
    public DataResult<List<CarMaintenanceGetDto>> getAllPaged(@RequestParam("pageNo") int pageNo,
                                                              @RequestParam("pageSize") int pageSize) {
        return this.carMaintenanceService.getAllPaged(pageNo, pageSize);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody CarMaintenanceUpdateDto carMaintenanceUpdateDto) {
        return this.carMaintenanceService.update(id, carMaintenanceUpdateDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) {
        return this.carMaintenanceService.delete(id);
    }
}
