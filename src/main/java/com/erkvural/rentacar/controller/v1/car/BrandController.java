package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.BrandCreateDto;
import com.erkvural.rentacar.dto.car.delete.BrandDeleteDto;
import com.erkvural.rentacar.dto.car.get.BrandGetDto;
import com.erkvural.rentacar.dto.car.update.BrandUpdateDto;
import com.erkvural.rentacar.service.car.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/car/brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody BrandCreateDto brandCreateDto) throws BusinessException {
        return this.brandService.add(brandCreateDto);
    }


    @GetMapping("/getAll")
    public DataResult<List<BrandGetDto>> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/get")
    public DataResult<BrandGetDto> get(@RequestParam("id") long id) throws BusinessException {
        return brandService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody BrandUpdateDto brandUpdateDto) throws BusinessException {
        return this.brandService.update(id, brandUpdateDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.brandService.delete(id);
    }
}
