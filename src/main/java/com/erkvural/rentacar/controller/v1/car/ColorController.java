package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.ColorCreateRequest;
import com.erkvural.rentacar.dto.car.get.ColorGetResponse;
import com.erkvural.rentacar.dto.car.update.ColorUpdateRequest;
import com.erkvural.rentacar.service.car.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car/colors")
public class ColorController {
    private final ColorService service;

    @Autowired
    public ColorController(ColorService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result add(@RequestBody  @Valid ColorCreateRequest createRequest) {
        return this.service.add(createRequest);
    }

    @GetMapping("/get/all")
    public DataResult<List<ColorGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<ColorGetResponse> get(@PathVariable long id) {
        return service.getById(id);
    }

    @PutMapping("/update/id={id}")
    public Result update(@PathVariable long id, @RequestBody  @Valid  ColorUpdateRequest updateRequest) {
        return this.service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/id={id}")
    public Result delete(@PathVariable long id) {
        return this.service.delete(id);
    }
}
