package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CityCreateDto;
import com.erkvural.rentacar.dto.car.delete.CityDeleteDto;
import com.erkvural.rentacar.dto.car.get.CityGetDto;
import com.erkvural.rentacar.dto.car.update.CityUpdateDto;
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
    public Result add(@RequestBody CityCreateDto cityCreateDto) {
        return this.cityService.add(cityCreateDto);
    }

    @GetMapping("/getAll")
    public DataResult<List<CityGetDto>> getAll() {
        return cityService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CityGetDto> get(@RequestParam("id") long id) {
        return cityService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody CityUpdateDto cityUpdateDto) {
        return this.cityService.update(id, cityUpdateDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) {
        return this.cityService.delete(id);
    }
}
