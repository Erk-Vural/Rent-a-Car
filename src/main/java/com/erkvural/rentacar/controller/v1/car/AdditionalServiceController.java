package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.AdditionalServiceCreateRequest;
import com.erkvural.rentacar.dto.car.get.AdditionalServiceGetResponse;
import com.erkvural.rentacar.dto.car.update.AdditionalServiceUpdateRequest;
import com.erkvural.rentacar.service.car.AdditionalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car/additional-services")
public class AdditionalServiceController {

    private final AdditionalServiceService service;

    @Autowired
    public AdditionalServiceController(AdditionalServiceService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result add(@RequestBody  @Valid AdditionalServiceCreateRequest createRequest) {
        return this.service.add(createRequest);
    }

    @Cacheable("additionalServices")
    @GetMapping("/get/all")
    public DataResult<List<AdditionalServiceGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<AdditionalServiceGetResponse> get(@PathVariable long id) {
        return service.getById(id);
    }

    @PutMapping("/update/id={id}")
    public Result update(@PathVariable long id, @RequestBody  @Valid  AdditionalServiceUpdateRequest updateRequest) {
        return this.service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/id={id}")
    public Result delete(@PathVariable long id) {
        return this.service.delete(id);
    }
}
