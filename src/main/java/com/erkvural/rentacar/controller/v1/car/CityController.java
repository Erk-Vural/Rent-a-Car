package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CityCreateRequest;
import com.erkvural.rentacar.dto.car.get.CityGetResponse;
import com.erkvural.rentacar.dto.car.update.CityUpdateRequest;
import com.erkvural.rentacar.service.car.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CityCreateRequest cityCreateDto) throws BusinessException {
        return this.cityService.add(cityCreateDto);
    }

    @GetMapping("/getAll")
    public DataResult<List<CityGetResponse>> getAll() {
        return cityService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CityGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return cityService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody CityUpdateRequest cityUpdateDto) throws BusinessException {
        return this.cityService.update(id, cityUpdateDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.cityService.delete(id);
    }
}
