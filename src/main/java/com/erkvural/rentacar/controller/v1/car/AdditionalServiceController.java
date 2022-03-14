package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.AdditionalServiceCreateDto;
import com.erkvural.rentacar.dto.car.get.AdditionalServiceGetDto;
import com.erkvural.rentacar.dto.car.update.AdditionalServiceUpdateDto;
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
    public Result add(@RequestBody AdditionalServiceCreateDto additionalServiceCreateDto) throws BusinessException {
        return this.additionalServiceService.add(additionalServiceCreateDto);
    }


    @GetMapping("/getAll")
    public DataResult<List<AdditionalServiceGetDto>> getAll() {
        return additionalServiceService.getAll();
    }

    @GetMapping("/get")
    public DataResult<AdditionalServiceGetDto> get(@RequestParam("id") long id) throws BusinessException {
        return additionalServiceService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody AdditionalServiceUpdateDto additionalServiceUpdateDto) throws BusinessException {
        return this.additionalServiceService.update(id, additionalServiceUpdateDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.additionalServiceService.delete(id);
    }
}
