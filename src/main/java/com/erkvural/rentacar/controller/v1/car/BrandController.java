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

    private final BrandService service;

    @Autowired
    public BrandController(BrandService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result add(@RequestBody BrandCreateRequest createRequest) {
        return this.service.add(createRequest);
    }

    @GetMapping("/get/all")
    public DataResult<List<BrandGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<BrandGetResponse> get(@PathVariable long id) {
        return service.getById(id);
    }

    @PutMapping("/update/id={id}")
    public Result update(@PathVariable long id, @RequestBody BrandUpdateRequest updateRequest) {
        return this.service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/id={id}")
    public Result delete(@PathVariable long id) throws BusinessException {
        return this.service.delete(id);
    }
}
