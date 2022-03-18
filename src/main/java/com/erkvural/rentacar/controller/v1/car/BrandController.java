package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.BrandCreateRequest;
import com.erkvural.rentacar.dto.car.get.BrandGetResponse;
import com.erkvural.rentacar.dto.car.update.BrandUpdateRequest;
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
    public Result add(@RequestBody BrandCreateRequest brandCreateDto) throws BusinessException {
        return this.brandService.add(brandCreateDto);
    }


    @GetMapping("/getAll")
    public DataResult<List<BrandGetResponse>> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/get")
    public DataResult<BrandGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return brandService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody BrandUpdateRequest brandUpdateDto) throws BusinessException {
        return this.brandService.update(id, brandUpdateDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.brandService.delete(id);
    }
}
