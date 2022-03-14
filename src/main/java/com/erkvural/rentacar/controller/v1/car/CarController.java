package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarCreateDto;
import com.erkvural.rentacar.dto.car.delete.CarDeleteDto;
import com.erkvural.rentacar.dto.car.get.CarGetDto;
import com.erkvural.rentacar.dto.car.update.CarUpdateDto;
import com.erkvural.rentacar.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CarCreateDto carCreateDto) {
        return this.carService.add(carCreateDto);
    }


    @GetMapping("/getAll")
    public DataResult<List<CarGetDto>> getAll() {
        return carService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CarGetDto> get(@RequestParam("id") long id) throws BusinessException {
        return carService.getById(id);
    }

    @GetMapping("/getAllSorted")
    public DataResult<List<CarGetDto>> getAllSorted(@RequestParam("direction") Sort.Direction direction) {
        return this.carService.getAllSorted(direction);
    }

    @GetMapping("/getAllPaged")
    public DataResult<List<CarGetDto>> getAllPaged(@RequestParam("pageNo") int pageNo,
                                                   @RequestParam("pageSize") int pageSize) {
        return this.carService.getAllPaged(pageNo, pageSize);
    }

    @GetMapping("/getAllByDailyPriceLessThanEqual")
    public DataResult<List<CarGetDto>> getAllByDailyPriceLessThanEqual(@RequestParam("dailyPrice") double dailyPrice) {
        return this.carService.getAllByDailyPriceLessThanEqual(dailyPrice);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody CarUpdateDto carUpdateDto) throws BusinessException {
        return this.carService.update(id, carUpdateDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.carService.delete(id);
    }
}