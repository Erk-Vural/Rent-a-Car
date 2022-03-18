package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.ColorCreateRequest;
import com.erkvural.rentacar.dto.car.get.ColorGetResponse;
import com.erkvural.rentacar.dto.car.update.ColorUpdateRequest;
import com.erkvural.rentacar.service.car.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car/colors")
public class ColorController {
    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody ColorCreateRequest createRequest) throws BusinessException {
        return this.colorService.add(createRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<ColorGetResponse>> getAll() {
        return colorService.getAll();
    }

    @GetMapping("/get")
    public DataResult<ColorGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return colorService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody ColorUpdateRequest updateRequest) throws BusinessException {
        return this.colorService.update(id, updateRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.colorService.delete(id);
    }
}
