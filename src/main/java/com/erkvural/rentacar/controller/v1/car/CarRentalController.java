package com.erkvural.rentacar.controller.v1.car;


import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarRentalCreateDto;
import com.erkvural.rentacar.dto.car.get.CarRentalGetDto;
import com.erkvural.rentacar.dto.car.update.CarRentalUpdateDto;
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
    public Result add(@RequestBody CarRentalCreateDto carMaintenanceCreateDto) {
        return this.carRentalService.add(carMaintenanceCreateDto);
    }

    @GetMapping("/getAll")
    public DataResult<List<CarRentalGetDto>> getAll() {
        return carRentalService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CarRentalGetDto> get(@RequestParam("id") long id) {
        return carRentalService.getById(id);
    }

    @GetMapping("/getByCarId")
    public DataResult<CarRentalGetDto> getByCarId(@RequestParam("id") int id) {
        return carRentalService.getByCarId(id);
    }

    @GetMapping("/getByCustomerId")
    public DataResult<CarRentalGetDto> getByCustomerId(@RequestParam("id") int id) {
        return carRentalService.getByCustomerId(id);
    }

    @GetMapping("/getAllStartDateSorted")
    public DataResult<List<CarRentalGetDto>> getAllStartDateSorted(@RequestParam("direction") Sort.Direction direction) {
        return this.carRentalService.getAllStartDateSorted(direction);
    }

    @GetMapping("/getAllEndDateSorted")
    public DataResult<List<CarRentalGetDto>> getAllEndDateSorted(@RequestParam("direction") Sort.Direction direction) {
        return this.carRentalService.getAllEndDateSorted(direction);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody CarRentalUpdateDto carRentalUpdateDto) {
        return this.carRentalService.update(id, carRentalUpdateDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) {
        return this.carRentalService.delete(id);
    }
}
