package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CarCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarGetResponse;
import com.erkvural.rentacar.dto.car.update.CarUpdateRequest;
import com.erkvural.rentacar.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car/cars")
public class CarController {
    private final CarService service;

    @Autowired
    public CarController(CarService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CarCreateRequest createRequest) throws BusinessException {
        return this.service.add(createRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<CarGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<CarGetResponse> get(@PathVariable long id) throws BusinessException {
        return service.getById(id);
    }

    @GetMapping("/getAllSorted/direction={direction}")
    public DataResult<List<CarGetResponse>> getAllSorted(@PathVariable Sort.Direction direction) {
        return this.service.getAllSorted(direction);
    }

    @GetMapping("/getAllPaged/pageNo={pageNo}+pageSize{pageSize}")
    public DataResult<List<CarGetResponse>> getAllPaged(@PathVariable int pageNo,
                                                        @PathVariable int pageSize) {
        return this.service.getAllPaged(pageNo, pageSize);
    }

    @GetMapping("/getAllByDailyPriceLessThanEqual/dailyPrice={dailyPrice}")
    public DataResult<List<CarGetResponse>> getAllByDailyPriceLessThanEqual(@PathVariable double dailyPrice) {
        return this.service.getAllByDailyPriceLessThanEqual(dailyPrice);
    }

    @PutMapping("/update/id={id}")
    public Result update(@PathVariable long id, @RequestBody CarUpdateRequest updateRequest) throws BusinessException {
        return this.service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/id={id}")
    public Result delete(@PathVariable long id) throws BusinessException {
        return this.service.delete(id);
    }
}
