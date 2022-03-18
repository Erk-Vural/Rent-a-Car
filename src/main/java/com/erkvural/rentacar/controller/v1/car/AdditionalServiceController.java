package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.AdditionalServiceCreateRequest;
import com.erkvural.rentacar.dto.car.get.AdditionalServiceGetResponse;
import com.erkvural.rentacar.dto.car.update.AdditionalServiceUpdateRequest;
import com.erkvural.rentacar.service.car.AdditionalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car/additional-services")
public class AdditionalServiceController {

    private final AdditionalServiceService additionalServiceService;

    @Autowired
    public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody AdditionalServiceCreateRequest createRequest) throws BusinessException {
        return this.additionalServiceService.add(createRequest);
    }


    @GetMapping("/getAll")
    public DataResult<List<AdditionalServiceGetResponse>> getAll() {
        return additionalServiceService.getAll();
    }

    @GetMapping("/get")
    public DataResult<AdditionalServiceGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return additionalServiceService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody AdditionalServiceUpdateRequest updateRequest) throws BusinessException {
        return this.additionalServiceService.update(id, updateRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.additionalServiceService.delete(id);
    }
}
